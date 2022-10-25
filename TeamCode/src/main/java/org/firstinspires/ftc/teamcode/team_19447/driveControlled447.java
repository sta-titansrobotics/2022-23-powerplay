package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class driveControlled447 extends LinearOpMode {

    @Override
    public void runOpMode() {


        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse right side motors
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        //Lift (Two lifts)
        DcMotor Lift1 = hardwareMap.get(DcMotor.class, "Lift 1");
        Lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Lift2 = hardwareMap.get(DcMotor.class, "Lift 2");
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Turret
        DcMotor Turret = hardwareMap.get(DcMotor.class, "turret");
        Turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Roller Flipper
        DcMotor rollerFlipper = hardwareMap.get(DcMotor.class, "rollerFlipper");
        rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double flipperMotorPower;

        //Intake
        DcMotor intake1 = hardwareMap.get(DcMotor.class, "intake1");
        DcMotor intake2 = hardwareMap.get(DcMotor.class, "intake2");
        DcMotor intake3 = hardwareMap.get(DcMotor.class, "intake3");
        DcMotor intake4 = hardwareMap.get(DcMotor.class, "intake4");

        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive()) {

            //Driving

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!

            //STRAFING VARIABLE
            double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing

            //THIS IS THE TURNING VARIABLE
            double rx = gamepad1.left_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFL.setPower(frontLeftPower);
            motorBL.setPower(backLeftPower);
            motorFR.setPower(frontRightPower);
            motorBR.setPower(backRightPower);

            if (gamepad1.dpad_up) {
                motorFL.setPower(1); //change this to infinity
                motorBL.setPower(1);
                motorFR.setPower(1);
                motorBR.setPower(1);
            }

            if (gamepad1.dpad_down) {
                motorFL.setPower(-1);
                motorBL.setPower(-1);
                motorFR.setPower(-1);
                motorBR.setPower(-1);
            }

            if (gamepad1.dpad_left) {
                motorFL.setPower(-1);
                motorBL.setPower(1);
                motorFR.setPower(1);
                motorBR.setPower(-1);
            }

            if (gamepad1.dpad_right) {
                motorFL.setPower(1);
                motorBL.setPower(-1);
                motorFR.setPower(-1);
                motorBR.setPower(1);
            }

            //Lift (apparently two lifts?)
            double liftPower = gamepad1.right_stick_y;

            Lift1.setPower(liftPower);
            Lift2.setPower(liftPower);

            //Turret
            double turretPower = gamepad1.right_stick_x;

            Turret.setPower(turretPower);

            //a lot of other stuff will be added too unfortunately

            //Roller Flipper

            flipperMotorPower = gamepad1.touchpad_finger_1_y;
            flipperMotorPower = Range.clip(flipperMotorPower, -1, 1);
            rollerFlipper.setPower(flipperMotorPower);

            //Intake
            double intakePower = gamepad1.touchpad_finger_1_x;

            intake1.setPower(intakePower);
            intake2.setPower(intakePower);
            intake3.setPower(intakePower);
            intake4.setPower(intakePower);

            }

            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("Lift Power:",Lift1.getPower());
            telemetry.addData("Lift Power:",Lift2.getPower());
            telemetry.addData("Lift Encoder Position: ", Lift1.getCurrentPosition());
            telemetry.addData("Lift Encoder Position: ", Lift2.getCurrentPosition());
            telemetry.update();


        }
    }
