package org.firstinspires.ftc.teamcode.team_19447;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class crservotest extends LinearOpMode {

    CRServo LeftArm;
    CRServo RightArm;

    @Override
    public void runOpMode() {
        LeftArm = hardwareMap.get(CRServo.class, "LeftArm");
        RightArm = hardwareMap.get(CRServo.class, "RightArm");

        RightArm.setDirection(CRServo.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {

            if (gamepad1.left_stick_y < 0) {
                LeftArm.setPower(1);
                RightArm.setPower(1);
            }

            else if (gamepad1.left_stick_y > 0) {
                LeftArm.setPower(-1);
                RightArm.setPower(-1);
            }

            else {
                LeftArm.setPower(0);
                RightArm.setPower(0);
            }

            /*
            if (gamepad1.left_stick_y < 0) {
                LeftArm.setPosition(1);
                RightArm.setPosition(1);
            }

            else if (gamepad1.left_stick_y > 0) {
                LeftArm.setPosition(0);
                RightArm.setPosition(0);
            }

            else {
                LeftArm.setPosition(0.5);
                RightArm.setPosition(0.5);
            }
             */

            telemetry.addData("Left Arm: ",LeftArm.getPower());
            telemetry.addData("Right Arm: ",RightArm.getPower());
            //telemetry.addData("Left Arm: ",LeftArm.getPosition());
            //telemetry.addData("Right Arm: ",RightArm.getPosition());
            telemetry.update();
        }

    }

}
