package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaCurrentGame;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame;

import java.util.List;

@Autonomous
public class AutoCameraTest extends LinearOpMode {
    private DcMotor LF, RF, LB, RB, Arm, Intake, Carousel, Turret;
    //private Timer time = new Timer();
    private VuforiaCurrentGame vuforiaPowerPlay;
    private TfodCurrentGame tfodPowerPlay;
    private List<Recognition> detection;

    @Override
    public void runOpMode() {

        /*
        //Initialize motors
        LF = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        RF = hardwareMap.get(DcMotor.class, "motorFrontRight");
        LB = hardwareMap.get(DcMotor.class, "motorBackLeft");
        RB = hardwareMap.get(DcMotor.class, "motorBackRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        Intake = hardwareMap.get(DcMotor.class, "poggy");
        Arm = hardwareMap.get(DcMotor.class, "arm");
        Turret = hardwareMap.get(DcMotor.class, "Turret");

        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Turret.setDirection(DcMotorSimple.Direction.REVERSE);
         */

        // Camera initialization
        vuforiaPowerPlay = new VuforiaCurrentGame();
        tfodPowerPlay = new TfodCurrentGame();

        // Initialize vuforia/camera settings
        vuforiaPowerPlay.initialize(
                // Change following to team's vuforia key
                "AR2AC0f/////AAABmeUyCjXw4kWXoMsF3MMHPaoaznW2sTM6JoLUdEAFY9/AEccRV1K06eyTEGTY33sxNxR+ENfuzMWWp8d/BgCSdWFthGz402U8Cwn+P41y8aZQ1r4RHUCDQhDB8CdHAhQq7E2kWPqEa5BmzkGdPJid61lERjhee/HV3Kjd6zMuh9bzWxYNCCNcIwS62sRqKHXPWeUGgKlWvgCzxu1OBX0UwdXAUTRRUCxDmNKfP1pul8sPEpuFkvvbZrDGFO1rxUlPwlQQRdjk4Hmbh5iLhBIPk5e7QhidUnkjIv8+z6pIcH0Kt02HKJZdcSXDS4u2xjgqjrO5Z0FjmqCQaAZmSRk/j7hODq61lwfioDMxX9mfwd+t", // vuforiaLicenseKey
                hardwareMap.get(WebcamName.class, "Webcam 1"), // Change this to the camera name, default "Webcam 1"
                "", // webcamCalibrationFilename
                true, // useExtendedTracking
                false, // enableCameraMonitoring
                VuforiaLocalizer.Parameters.CameraMonitorFeedback.NONE, // cameraMonitorFeedback
                0, // dx
                0, // dy
                0, // dz
                AxesOrder.XZY, // axesOrder
                90, // firstAngle
                90, // secondAngle
                0, // thirdAngle
                true // useCompetitionFieldTargetLocations
        );

        // Confidence threshold (Change for stricter requirements)
        tfodPowerPlay.initialize(vuforiaPowerPlay, (float) 0.3, true, true);

        // Initialize TFOD before waitForStart.
        // Init TFOD here so the object detection labels are visible
        // in the Camera Stream preview window on the Driver Station.
        tfodPowerPlay.activate();
        // Enable following block to zoom in on target.

        waitForStart();

        // Output if label detected
        telemetry.addData("Detected? ", labelDetection() );
        telemetry.update();
    }

    /*
    * @author Gregory Lui
    * @description
    * */
    private boolean labelDetection(){

        // Initialize Model checker
        detection = tfodPowerPlay.getRecognitions();

        //
        boolean labelDetected = false;

        // Loop through list of objects detected
        while (detection.size()==0) {
            detection = tfodPowerPlay.getRecognitions();
            // Output list of detected objects
            telemetry.addData("detections: ", detection);

            if (detection.size()>0) {
                // Should only detect 1 label.
                labelDetected = true;
            }

        }
        // Returns true/false of object detected
        return labelDetected;

    }

    public void move(double LF, double RF, double LB, double RB, int sleepMS) {
        this.LF.setPower(LF);
        this.LB.setPower(LB);
        this.RF.setPower(RF);
        this.RB.setPower(RB);
        sleep(sleepMS);
    }
}
