// FINALIZED DRIVE CONTROLLED, DO NOT TOUCH ANYTHING HERE //

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class amazingRace447 extends LinearOpMode {

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse left side motors
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

        double power1 = 0;
        double power2 = 0;

        waitForStart();

        if (isStopRequested())
            return;

        while (opModeIsActive()) {

            //Driving

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!

            //STRAFING VARIABLE
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing

            //THIS IS THE TURNING VARIABLE
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFL.setPower(frontLeftPower);
            motorBL.setPower(backLeftPower);
            motorFR.setPower(frontRightPower);
            motorBR.setPower(backRightPower);

            if (timer.seconds() % 60 == 0) {
                power1 = Math.random();
                power2 = Math.random();
            }

            if (gamepad1.dpad_up) {
                motorFL.setPower(power1); //change this to infinity
                motorBL.setPower(power1);
                motorFR.setPower(power2);
                motorBR.setPower(power2);
            }

            if (gamepad1.dpad_down) {
                motorFL.setPower(-power1);
                motorBL.setPower(-power1);
                motorFR.setPower(-power2);
                motorBR.setPower(-power2);
            }

            if (gamepad1.dpad_left) {
                motorFL.setPower(-power1);
                motorBL.setPower(power1);
                motorFR.setPower(power2);
                motorBR.setPower(-power2);
            }

            if (gamepad1.dpad_right) {
                motorFL.setPower(power1);
                motorBL.setPower(-power1);
                motorFR.setPower(-power2);
                motorBR.setPower(power2);
            }

            while (timer.seconds() >= 300) {
                timer.reset();
                motorFL.setPower(0);
                motorBL.setPower(0);
                motorFR.setPower(0);
                motorBR.setPower(0);
            }

            //Telemetry
            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("LF Position:", motorFL.getCurrentPosition());
            telemetry.addData("LB Position:", motorBL.getCurrentPosition());
            telemetry.addData("RF Position:", motorFR.getCurrentPosition());
            telemetry.addData("RB Position:", motorBR.getCurrentPosition());
            telemetry.addData("Current Timer: ", timer.seconds());
            telemetry.update();
        }
    }
}