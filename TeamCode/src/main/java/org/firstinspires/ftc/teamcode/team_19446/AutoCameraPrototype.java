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
public class AutoCameraPrototype extends LinearOpMode {
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

        // CHANGE HERE FOR CONE DETECTION AND PARKING CONTROLS
        // default parking spot
        if (parkingDetection() == 0) {

        // parking space 1
        } else if (parkingDetection()  == 1) {

        // parking space 2
        } else if (parkingDetection() ==2) {

        // parking space 3
        } else if (parkingDetection()  == 3) {

        }

        // Output detecting parking value
        telemetry.addData("parking ", parkingDetection() );
        telemetry.update();
    }

    /*
    * @author Gregory Lui
    * @description cross checks labels in database with labels detected by camera. Outputs value to determine parking space for PowerPlay
    * */
    private int parkingDetection(){

        // Initialize Model checker
        detection = tfodPowerPlay.getRecognitions();

        // Parking object detection
        // Default parking value is set to 0
        // This value is used to go to the normal corner
        // If the robot does not detect any objects
        int parkingSpot = 0;

        // Loop through list of objects detected
        while (detection.size()==0) {
            detection = tfodPowerPlay.getRecognitions();
            // Output list of detected objects
            telemetry.addData("detections: ", detection);

            if (detection.size()>0) {
                // loop through list of detected labels (Should only detect 1 label at a time)
                for (int i = 0; i < detection.size(); i++) {
                    // Create string to check name of label detected
                    String detectedLabel = detection.get(i).getLabel();

                    // Change Data of string within .equals("") to names of labels within .xml file
                    if (detectedLabel.equals("TFODTest 2")) {
                        parkingSpot = 1;

                    } else if (detectedLabel.equals("TFODTest 3")) {
                        parkingSpot = 2;

                    } else if (detectedLabel.equals("TFODTest 4")) {
                        parkingSpot = 3;

                    } else {
                        telemetry.addData("NO DETECTIONS", "");

                    }
                }
            }
            telemetry.update();

        }
        // Returns value of object detected to determine parking space
        return parkingSpot;

    }

    public void move(double LF, double RF, double LB, double RB, int sleepMS) {
        this.LF.setPower(LF);
        this.LB.setPower(LB);
        this.RF.setPower(RF);
        this.RB.setPower(RB);
        sleep(sleepMS);
    }
}
