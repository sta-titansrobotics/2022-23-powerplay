package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class PIDControllerDrive extends LinearOpMode {

    DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

    double integralSum = 0;

    double Kp = 0.03;
    double Ki = 0.0003;
    double Kd = 0;
    double Kf = 0;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    @Override
    public void runOpMode() {
        leftFrontMotor = hardwareMap.get(DcMotorEx.class, "motorFrontLeft");
        leftBackMotor = hardwareMap.get(DcMotorEx.class, "motorBackLeft");
        rightFrontMotor = hardwareMap.get(DcMotorEx.class, "motorFrontRight");
        rightBackMotor = hardwareMap.get(DcMotorEx.class, "motorBackRight");

        leftFrontMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        forwardTicks(1000);

        while (opModeIsActive()) {
            telemetry.addData("LF Encoder:", leftFrontMotor.getCurrentPosition());
            telemetry.addData("LB Encoder:", leftBackMotor.getCurrentPosition());
            telemetry.addData("RF Encoder:", rightFrontMotor.getCurrentPosition());
            telemetry.addData("RB Encoder:", rightBackMotor.getCurrentPosition());
            telemetry.update();

        }
    }

    // reference is encoder ticks, state is current position
    public double PID(double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double power = (error * Kp) + (derivative * Kd) + (integralSum * Ki) + (reference * Kf);
        return power;
    }

    public void forwardTicks(double reference) {
        leftFrontMotor.setPower(PID(reference, leftFrontMotor.getCurrentPosition()));
        leftBackMotor.setPower(PID(reference, leftBackMotor.getCurrentPosition()));
        rightFrontMotor.setPower(PID(reference, rightFrontMotor.getCurrentPosition()));
        rightBackMotor.setPower(PID(reference, rightBackMotor.getCurrentPosition()));
    }
}