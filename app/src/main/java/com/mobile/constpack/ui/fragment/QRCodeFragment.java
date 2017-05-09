package com.mobile.constpack.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.mobile.constpack.R;
import com.mobile.constpack.helpers.Constants;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.response.TasksResponse;
import com.mobile.constpack.ui.activity.TaskController_;
import com.mobile.constpack.ui.dialogs.AlertDialogBuilder;
import com.mobile.constpack.ui.views.CameraSourcePreview;
import com.mobile.constpack.ui.views.GraphicOverlay;
import com.mobile.constpack.utils.DialogUtil;
import com.mobile.constpack.utils.ScreenUtils;
import com.mobile.constpack.utils.StringUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import lombok.Setter;

/**
 * Created by buraksoykal on 31/01/2017.
 */


@EFragment(R.layout.activity_qr)
public class QRCodeFragment extends BaseFragment implements Detector.Processor<Barcode> {


    @ViewById(R.id.camera_preview)
    CameraSourcePreview cameraPreview;

    @ViewById(R.id.focus_overlay)
    GraphicOverlay focusOverlay;

    @SystemService
    Vibrator vibrator;

    @StringRes(R.string.qr_detector_low_storage_error)
    String barcodeDetectorLowStorageError;

    @StringRes(R.string.error_title)
    String errorTitle;

    @Setter
    TabLayout tabLayout;
    private CameraSource cameraSource;
    private boolean isBarcodeFound = false;
    private ToneGenerator toneGenerator;
    private BarcodeDetector barcodeDetector;
    private boolean skipToneGenerator = false;

    @Override
    public void initViews() {
        try {
            toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
        } catch (Exception e) {
            skipToneGenerator = true;
        }
        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.QR_CODE).build();
        barcodeDetector.setProcessor(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        cameraSource = new CameraSource.Builder(getActivity(), barcodeDetector)
                .setRequestedPreviewSize(displaymetrics.widthPixels, displaymetrics.heightPixels - ScreenUtils.getStatusBarHeight(getActivity()))
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true).build();

        if (!barcodeDetector.isOperational()) {
            /**
             * Note: The first time that an app using the barcode or face API is installed on a
             * device, GMS will download a native libraries to the device in order to do detection.
             * Usually this completes before the app is run for the first time.  But if that
             * download has not yet completed, then the above call will not detect any barcodes
             * and/or faces.
             *
             * isOperational() can be used to check if the required native libraries are currently
             * available.  The detectors will automatically become operational once the library
             * downloads complete on device.
             * Check for low storage.  If there is low storage, the native library will not be
             * downloaded, so detection will not become operational.
             */

            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            if (getActivity().registerReceiver(null, lowstorageFilter) != null)
                showLowStorageDialog();
        }
    }

    private void showLowStorageDialog() {
        DialogUtil.showCommonAlertDialog(getActivity(), errorTitle, barcodeDetectorLowStorageError, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.finishAfterTransition(getActivity());
            }
        });
    }

    @Override
    public void release() {
    }

    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        final int toneVibratorTime = 250;
        final SparseArray<Barcode> barcodes = detections.getDetectedItems();

        if (barcodes.size() != 0 && !isBarcodeFound) {
            isBarcodeFound = true;
            cameraPreview.post(new Runnable() {
                public void run() {
                    cameraPreview.stop();
                    handleFoundedBarcode(barcodes.valueAt(0));
                    if (!skipToneGenerator)
                        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, toneVibratorTime);
                    vibrator.vibrate(toneVibratorTime);
                }
            });
        }
    }

    private void handleFoundedBarcode(Barcode barcode) {
        String jsonString = barcode.displayValue;
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            showInvalidBarcodeMsg(barcode);
            return;
        }

        String locusId = jsonObj.optString("locusId", "");

        if (StringUtils.isEmpty(locusId)) {
            showInvalidBarcodeMsg(barcode);
            return;
        }

        focusOverlay.setBarcode(barcode, true);
        getBaseController().showLoadingDialog();
        RestManager.getInstance().requestTasks(locusId).enqueue(new BaseCallback<TasksResponse>(getActivity()) {
            @Override
            public void onSuccess(TasksResponse response) {
                getBaseController().dismissLoadingDialog();
                TaskController_.intent(getActivity()).tasks(response.getTasks()).isEditable(true).startForResult(Constants.FINISH_FRAGMENT_CODE);
            }

            @Override
            public void onFailure(int errorId, String error) {
                getBaseController().dismissLoadingDialog();
                DialogUtil.showCommonAlertDialog(getActivity(), getString(R.string.error_title), "Mahal Bulunamadı !!", new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        isBarcodeFound = false;
                        if (tabLayout != null)
                            tabLayout.getTabAt(1).select();
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (cameraSource != null) {
            try {
                cameraPreview.start(cameraSource, focusOverlay);
            } catch (IOException e) {
                cameraPreview.release();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraPreview.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (barcodeDetector != null) {
            barcodeDetector.release();
            barcodeDetector = null;
        }
        if (cameraPreview != null)
            cameraPreview.release();

        isBarcodeFound = false;
    }

    private JSONObject checkQrExtraForAmount(String groupQrExtra) {
        if (groupQrExtra.isEmpty())
            return null;
        try {
            return new JSONObject(groupQrExtra);
        } catch (JSONException e) {
            return null;
        }
    }


    private void showInvalidBarcodeMsg(Barcode barcode) {

        new AlertDialogBuilder(getActivity()).setTitle(R.string.error_title).setMessage(R.string.invalid_qr_msg)
                .setPositiveButton(R.string.ok_button, null).setCancelable(false)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        focusOverlay.setBarcode(null, false);
                        isBarcodeFound = false;
                        if (tabLayout != null) tabLayout.getTabAt(1).select();
                    }
                }).show();
    }

}
