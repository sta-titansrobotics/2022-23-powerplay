package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Automonous447 extends LinearOpMode {

    DcMotor leftF, leftR, rightR, rightF;
    public static double forwardTicks;
    public static double strafeTicks;

    public Automonous447(double forward, double strafe, DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB) {
        forwardTicks = forward;
        strafeTicks = strafe;
        leftF = LF;
        leftR = LB;
        rightF = RF;
        rightR = RB;
    }

    /**
     * Moves robot forward
     * @param distanceCM desired distance travelled
     */
    public void forward(double distanceCM) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(ticks);
        leftR.setTargetPosition(ticks);
        rightR.setTargetPosition(ticks);
        rightF.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(rightR.isBusy() && rightF.isBusy() && leftR.isBusy() && leftF.isBusy()) {
        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void backward(double distanceCM) {

        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(-ticks);
        leftR.setTargetPosition(-ticks);
        rightR.setTargetPosition(-ticks);
        rightF.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftF.isBusy() && leftR.isBusy() && rightR.isBusy() && rightF.isBusy()) {

        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void leftStrafe(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(-ticks);
        leftR.setTargetPosition(ticks);
        rightR.setTargetPosition(-ticks);
        rightF.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftF.isBusy() && leftR.isBusy() && rightR.isBusy() && rightF.isBusy()) {

        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void rightStrafe(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(ticks);
        leftR.setTargetPosition(-ticks);
        rightR.setTargetPosition(ticks);
        rightF.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftF.isBusy() && leftR.isBusy() && rightR.isBusy() && rightF.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnLeft(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(-ticks);
        leftR.setTargetPosition(-ticks);
        rightR.setTargetPosition(ticks);
        rightF.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftF.isBusy() && leftR.isBusy() && rightR.isBusy() && rightF.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnRight(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setTargetPosition(-ticks);
        leftR.setTargetPosition(-ticks);
        rightR.setTargetPosition(-ticks);
        rightF.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftF.isBusy() && leftR.isBusy() && rightR.isBusy() && rightF.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void motorOn(double power) {
        leftF.setPower(power);
        leftR.setPower(power);
        rightF.setPower(power);
        rightR.setPower(power);
    }

    public void stopDriving() {
        leftF.setPower(0);
        leftR.setPower(0);
        rightF.setPower(0);
        rightR.setPower(0);
    }

    public void setMode(DcMotor.RunMode mode) {
        leftF.setMode(mode);
        leftR.setMode(mode);
        rightF.setMode(mode);
        rightR.setMode(mode);
    }
    //This must be placed AFTER all of the creations of methods or else everything breaks
    public void runOpMode(){
        /*
        * Example Autonomous
        * Moves forward, goes left and right, goes back to original position
        */
        forward(50);
        leftStrafe(50);
        rightStrafe(100);
        leftStrafe(50);
        backward(50);

        //Logs encoder positions to telemetry
        while (opModeIsActive()) {

            telemetry.addData("encoder-front-left: ", leftF.getCurrentPosition());
            telemetry.addData("encoder-rear-left: ", leftR.getCurrentPosition());
            telemetry.addData("encoder-front-right: ", rightF.getCurrentPosition());
            telemetry.addData("encoder-rear-right: ", rightR.getCurrentPosition());
            telemetry.update();
        }
    }

}

