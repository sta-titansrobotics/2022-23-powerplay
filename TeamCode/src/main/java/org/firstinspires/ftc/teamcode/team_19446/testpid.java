package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class testpid extends LinearOpMode {

    public static double forwardticks = 52.3; // ticks per cm
    public static double forwardticks1 = 0.02492375;
    public static double strafeticks = 54.05; // ticks per cm when strafing
    public static double angleTicks = 7; // ticks per angle change for arm
    public static double radius = 42; // radius of arm
    public static double turretticks = 600; // ticks per 90 degrees for turret



    @Override
    public void runOpMode() {

        ElapsedTime PIDTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        //Moving
        DcMotorEx lf = hardwareMap.get(DcMotorEx.class, "motorFrontLeft");
        DcMotorEx lb = hardwareMap.get(DcMotorEx.class, "motorBackLeft");
        DcMotorEx rf = hardwareMap.get(DcMotorEx.class, "motorFrontRight");
        DcMotorEx rb = hardwareMap.get(DcMotorEx.class, "motorBackRight");

        //Reverse right side motors
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);


        //Cascading Arm
        /*
        DcMotorEx Arm = hardwareMap.get(class, "arm");
        

        Arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

         */


        waitForStart();

        PID(directions.FRONT, 100, 1, 1, PIDTimer, lf, lb, rf, rb);

        PID(directions.RIGHT, 100, 1, 1, PIDTimer, lf, lb, rf, rb);
        PID(directions.BACK, 100, 1, 1, PIDTimer, lf, lb, rf, rb);




    }


    public void setVelF(double vel, DcMotorEx lf, DcMotorEx lb, DcMotorEx rf, DcMotorEx rb) {
        lf.setVelocity(vel);
        lb.setVelocity(vel);
        rf.setVelocity(vel);
        rb.setVelocity(vel);
    }

    public void setVelS(double vel, DcMotorEx lf, DcMotorEx lb, DcMotorEx rf, DcMotorEx rb) {
        lf.setVelocity(-vel);
        lb.setVelocity(vel);
        rf.setVelocity(vel);
        rb.setVelocity(-vel);
    }



    public void arm() {} //gonna make this later




    public void PID (directions direction, double CM, double coefficientproportional, double coefficientderivative, ElapsedTime timer, DcMotorEx lf, DcMotorEx lb, DcMotorEx rf, DcMotorEx rb) {
        double Cd = coefficientderivative; // pid coefficient for derivative control
        double Cp = coefficientproportional; // pid coefficient for proportional control
        double d = 0; // derivative
        double p = 0; // proportional

        lf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        double lfT = lf.getCurrentPosition();
        double lbT = lf.getCurrentPosition();
        double rfT = rf.getCurrentPosition();
        double rbT = rb.getCurrentPosition();
        double lastError = 0;

        double tick = 0;
        double def = 0;



        boolean stop = false;

        if (direction == direction.FRONT) {

            tick = CM * forwardticks;

            def = tick/3;

            double currentVelocity = def;

            while (!stop) {
                stop = lfT < (tick + 10) && lfT > (tick - 10) && lbT < (tick + 10) && lbT > (tick - 10) && rbT < (tick + 10) && rbT > (tick - 10) && rfT < (tick + 10) && rfT > (tick - 10);
                timer.reset(); //resets the timer

                lfT = lf.getCurrentPosition();
                lbT = lb.getCurrentPosition();
                rfT = rf.getCurrentPosition();
                rbT = rb.getCurrentPosition();

                double error = tick - (lfT+lbT+rfT+rbT)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / timer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;


                setVelF((currentVelocity + p + d), lf, rf, lb, rb);

                currentVelocity = currentVelocity + p + d;

                lastError = error;

            }


        }

        else if (direction == direction.BACK) {

            tick = CM * forwardticks;

            def = tick/3;

            double currentVelocity = def;

            while (!stop) {
                stop = lfT < (tick + 10) || lfT > (tick - 10) || lbT < (tick + 10) || lbT > (tick - 10) || rbT < (tick + 10) || rbT > (tick - 10) || rfT < (tick + 10) || rfT > (tick - 10); timer.reset(); //resets the timer
                timer.reset();

                lfT = lf.getCurrentPosition();
                lbT = lb.getCurrentPosition();
                rfT = rf.getCurrentPosition();
                rbT = rb.getCurrentPosition();

                double error = tick - (lfT+lbT+rfT+rbT)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / timer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;

                setVelF(currentVelocity - p - d, lf, rf, lb, rb);

                currentVelocity = currentVelocity - p - d;

                lastError = error;

            }

        }
        else if (direction == direction.LEFT) {

            tick = CM * strafeticks;
            def = tick/3;

            double currentVelocity = def;

            while (!stop) {
                stop = lfT < (tick + 10) || lfT > (tick - 10) || lbT < (tick + 10) || lbT > (tick - 10) || rbT < (tick + 10) || rbT > (tick - 10) || rfT < (tick + 10) || rfT > (tick - 10);timer.reset(); //resets the timer
                timer.reset();

                lfT = lf.getCurrentPosition();
                lbT = lb.getCurrentPosition();
                rfT = rf.getCurrentPosition();
                rbT = rb.getCurrentPosition();

                double error = tick - (lfT+lbT+rfT+rbT)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / timer.time();


                p = error * Cp * def;

                d = derivative * Cd * def;

                setVelS((currentVelocity+ p + d), lf, rf, lb, rb);

                currentVelocity = currentVelocity + p + d;

                lastError = error;

            }

        }

        else if (direction == direction.RIGHT) {

            tick = CM * strafeticks;
            def = tick/3;

            double currentVelocity = def;

            while (!stop) {
                stop = lfT < (tick + 10) || lfT > (tick - 10) || lbT < (tick + 10) || lbT > (tick - 10) || rbT < (tick + 10) || rbT > (tick - 10) || rfT < (tick + 10) || rfT > (tick - 10);
                timer.reset(); //resets the timer

                lfT = lb.getCurrentPosition();
                lbT = lb.getCurrentPosition();
                rfT = rf.getCurrentPosition();
                rbT = rb.getCurrentPosition();

                double error = tick - (lfT+lbT+rfT+rbT)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / timer.time();


                p = error * Cp;

                d = derivative * def * Cd;

                setVelS(currentVelocity - p - d, lf, rf, lb, rb);

                currentVelocity = currentVelocity - p - d;

                lastError = error;

            }

        }
    }
}