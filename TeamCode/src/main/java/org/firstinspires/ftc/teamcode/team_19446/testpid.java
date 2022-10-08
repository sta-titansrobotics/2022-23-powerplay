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
        double derivativeC = coefficientderivative; // pid coefficient for derivative control
        double proportionalC = coefficientproportional; // pid coefficient for proportional control

        //Reset encoder ticks
        lf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        //get current position of each motor (should be 0 at this point)
        double lfTicks = lf.getCurrentPosition();
        double lbTicks = lf.getCurrentPosition();
        double rfTicks = rf.getCurrentPosition();
        double rbTicks = rb.getCurrentPosition();

        //initialize variables
        double lastError = 0;
        double tick = 0;
        boolean stop = false;

        //calculate ticks required to pass in order to get to given position (based on given direction)
        switch (direction) {
            case FRONT:
                tick = CM * forwardticks;
                break;
            case BACK:
                tick = -1 * CM * forwardticks;
                break;
            case RIGHT:
                tick = CM * strafeticks;
                break;
            case LEFT:
                tick = CM * -1 * strafeticks;
                break;
        }

        //Default speed increment
        double defaultSpeed = tick/3;

        double currentVelocity = defaultSpeed;

        while (!stop) {
            //Average tick position of each motor
            double averageTicks = (rbTicks + rfTicks + lbTicks + lfTicks)/4;

            //check if average ticks is within range (both bigger than ticks - 10, and smaller than ticks - 20)
            //gives a 20 tick buffer range
            stop = averageTicks > (tick - 10) && averageTicks < (tick - 10);

            timer.reset(); //resets the timer

            //Get position of each motor
            lfTicks = lf.getCurrentPosition();
            lbTicks = lb.getCurrentPosition();
            rfTicks = rf.getCurrentPosition();
            rbTicks = rb.getCurrentPosition();

            //Calculate how off each motor is on average
            double error = tick - (lfTicks+lbTicks+rfTicks+rbTicks)/4;

            //Use last calculation of error to see how error has changed over a given time
            double derivative = (error - lastError)/timer.time();

            //Calculating how much should be added to the current velocity using error (proportional), change of error (derivative), and coefficients
            double proportionalAdd = error * defaultSpeed * proportionalC;
            double derivativeAdd = derivative * derivativeC * defaultSpeed;

            //set velocity in the given direction
            switch (direction) {
                case FRONT:
                    setVelF((currentVelocity + proportionalAdd + derivativeAdd), lf, rf, lb, rb);
                    currentVelocity = currentVelocity + proportionalAdd + derivativeAdd;
                    break;
                case BACK:
                    setVelF(currentVelocity - proportionalAdd - derivativeAdd, lf, rf, lb, rb);
                    currentVelocity = currentVelocity - proportionalAdd - derivativeAdd;
                    break;
                case RIGHT:
                    setVelS(currentVelocity - proportionalAdd - derivativeAdd, lf, rf, lb, rb);
                    currentVelocity = currentVelocity - proportionalAdd - derivativeAdd;
                    break;
                case LEFT:
                    setVelS((currentVelocity+ proportionalAdd + derivativeAdd), lf, rf, lb, rb);
                    currentVelocity = currentVelocity + proportionalAdd + derivativeAdd;
                    break;
            }

            lastError = error;

        }
    }
}