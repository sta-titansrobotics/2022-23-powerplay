package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//blah blah blah

@Disabled
public class PIDControllerAngle extends LinearOpMode {

    DcMotorEx leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;


    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFrontMotor = hardwareMap.get(DcMotorEx.class, "motorFrontLeft");
        leftBackMotor = hardwareMap.get(DcMotorEx.class, "motorBackLeft");
        rightFrontMotor = hardwareMap.get(DcMotorEx.class, "motorFrontRight");
        rightBackMotor = hardwareMap.get(DcMotorEx.class, "motorBackRight");

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        waitForStart();

        double referenceAngle = Math.toRadians(90);
        while (opModeIsActive()) {

            double power = PID(referenceAngle, imu.getAngularOrientation().firstAngle);
            turning(power);

        }

    }

    // reference is encoder ticks, state is current position
    public double PID(double reference, double state) {
        double error = angleWrap(reference - state);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }

    public double angleWrap(double radians) {
        while (radians > Math.PI) {
            radians -= 2 * Math.PI;
        }
        while (radians < -Math.PI) {
            radians += 2 * Math.PI;
        }
        return radians;
    }

    public void turning(double power) {
        leftFrontMotor.setPower(power);
        leftBackMotor.setPower(power);
        rightFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
    }
}