package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class PIDControllerDrive extends LinearOpMode {

    DcMotorEx leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;
    double Kf = 0;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    @Override
    public void runOpMode() {
        leftFrontMotor = hardwareMap.get(DcMotorEx.class, "leftFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotorEx.class, "leftBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotorEx.class, "rightFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotorEx.class, "rightBackMotor");

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        forwardTicks(1000);

        /*
        while (opModeIsActive()) {
        }
         */

    }

    // reference is encoder ticks, state is current position
    public double PID(double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();

        timer.reset();

        return (error * Kp) + (derivative * Kd) + (integralSum * Ki) + (reference * Kf);
    }

    public void forwardTicks(double reference) {
        leftFrontMotor.setPower(PID(reference, leftFrontMotor.getCurrentPosition()));
        leftBackMotor.setPower(PID(reference, leftBackMotor.getCurrentPosition()));
        rightFrontMotor.setPower(PID(reference, rightFrontMotor.getCurrentPosition()));
        rightBackMotor.setPower(PID(reference, rightBackMotor.getCurrentPosition()));
    }
}