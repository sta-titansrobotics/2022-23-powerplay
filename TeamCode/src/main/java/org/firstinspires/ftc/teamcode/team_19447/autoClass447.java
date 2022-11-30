package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

public class autoClass447 {

    DcMotor FrontLeft;
    DcMotor RearLeft;
    DcMotor FrontRight;
    DcMotor RearRight;
    public static double forwardTicks;
    public static double strafeTicks;

    public autoClass447(double forward, double strafe, DcMotor FL, DcMotor BL, DcMotor FR, DcMotor BR) {
        forwardTicks = forward;
        strafeTicks = strafe;
        FrontLeft = FL;
        RearLeft = BL;
        FrontRight = FR;
        RearRight = BR;
    }

    //FUNCTIONS
    public void StrafeRight(int distanceCM, boolean NextSequence) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        /*
        Strafe Right
        FL: Forward
        FR: Reverse
        RL: Reverse
        RR: Forward
        */

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {

        }
    }

    public void StrafeLeft(int distanceCM, boolean NextSequence) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        /*
        Strafe Left
        FL: Reverse
        FR: Forward
        RL: Forward
        RR: Reverse
         */

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void Forward(int distanceCM, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearLeft.setPower(0);
        RearRight.setPower(0);
    }

    public void Backward(int distanceCM, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void TurnLeft(int distanceCM, double Speed, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(-Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(-Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearLeft.setPower(0);
        RearRight.setPower(0);

        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void TurnRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void forwardDiagonalLeft(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(0);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(0);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void forwardDiagonalRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(0);
        RearLeft.setTargetPosition(0);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void backwardDiagonalLeft(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(0);
        RearLeft.setTargetPosition(0);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void backwardDiagonalRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(0);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(0);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void ChassisReset() {
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    //is it possible to translate cm distance into the lift??

    public void setMode(DcMotor.RunMode mode) {
        FrontLeft.setMode(mode);
        RearLeft.setMode(mode);
        FrontRight.setMode(mode);
        RearRight.setMode(mode);
    }

    public void drive(int leftTarget1, int leftTarget2, int rightTarget1, int rightTarget2, double speed) {

        int leftPos1 = 0;
        int leftPos2 = 0;
        int rightPos1 = 0;
        int rightPos2 = 0;

        double forwardTicks = 52.3;
        double strafeTicks = 54.05;

        int ticks1 = (int) forwardTicks;
        int ticks2 = (int) strafeTicks;

        leftPos1 += leftTarget1; //By adding the "+=", it makes it equivalent to leftPos1 = leftPos1 + leftTarget1, therefore it will allow adding values to the position based on what the target is.
        leftPos2 += leftTarget2; //This will therefore change where the motor needs to be by the specific inputted amount
        rightPos1 += rightTarget1;
        rightPos2 += rightTarget2;

        // Using setTargetPosition and RUN_TO_POSITION, it forces motors to continue running until the encoders reach the specified target position
        // We are multiplying by forward/strafe ticks in relation to forward/strafe based on what the robot is doing.
        if ((leftPos1 >= 0) && (leftPos2 >= 0) && (rightPos1 >= 0) && (rightPos2 >= 0)) { //Forward
            FrontLeft.setTargetPosition((leftPos1) * ticks1);
            RearLeft.setTargetPosition((leftPos2) * ticks1);
            FrontRight.setTargetPosition((rightPos1) * ticks1);
            RearRight.setTargetPosition((rightPos2) * ticks1);

        } else if ((leftPos1 < 0) && (leftPos2 < 0) && (rightPos1 < 0) && (rightPos2 < 0)) { //Backwards
            FrontLeft.setTargetPosition((leftPos1) * ticks1);
            RearLeft.setTargetPosition((leftPos2) * ticks1);
            FrontRight.setTargetPosition((rightPos1) * ticks1);
            RearRight.setTargetPosition((rightPos2) * ticks1);

        } else if (((leftPos1 < 0) && (rightPos2 < 0) && (leftPos2 >= 0) && (rightPos1 >= 0)) || (((leftPos2 < 0) && (rightPos1 < 0)) && (leftPos1 >= 0) && (rightPos2 >= 0))) {
            // Strafing left or right
            FrontLeft.setTargetPosition((leftPos1) * ticks2);
            RearLeft.setTargetPosition((leftPos2) * ticks2);
            FrontRight.setTargetPosition((rightPos1) * ticks2);
            RearRight.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos2 > 0) && (rightPos1 > 0)) || ((leftPos1 > 0) && (rightPos2 > 0))){
            //Strafing diagonally forward right or left, respectively.
            FrontLeft.setTargetPosition((leftPos1) * ticks2);
            RearLeft.setTargetPosition((leftPos2) * ticks2);
            FrontRight.setTargetPosition((rightPos1) * ticks2);
            RearRight.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos2 < 0) && (rightPos1 < 0)) || ((leftPos1 < 0) && (rightPos2 < 0))) {
            //Strafing diagonally backward right or left, respectively.
            FrontLeft.setTargetPosition((leftPos1) * ticks2);
            RearLeft.setTargetPosition((leftPos2) * ticks2);
            FrontRight.setTargetPosition((rightPos1) * ticks2);
            RearRight.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos1 < 0) && (rightPos2 > 0) && (leftPos2 < 0) && (rightPos1 > 0)) || (((leftPos2 > 0) && (rightPos1 < 0)) && (leftPos1 > 0) && (rightPos2 < 0))) {
            //Turning left or right, respectively.
            FrontLeft.setTargetPosition((leftPos1) * ticks2);
            RearLeft.setTargetPosition((leftPos2) * ticks2);
            FrontRight.setTargetPosition((rightPos1) * ticks2);
            RearRight.setTargetPosition((rightPos2) * ticks2);

        }

        //Encoders do not change speed automatically. Need to adjust speed ourselves
        FrontLeft.setPower(speed);
        RearLeft.setPower(speed);
        FrontRight.setPower(speed);
        RearRight.setPower(speed);

        // The code gets stuck in between the Run to Position and the speed.
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //while loop to stall/delay the next command
        while(FrontLeft.isBusy() && RearLeft.isBusy() && FrontRight.isBusy() && RearRight.isBusy()) {

        }

        //Stop driving so that it can perform the next command.
        FrontLeft.setPower(0);
        RearLeft.setPower(0);
        FrontRight.setPower(0);
        RearRight.setPower(0);

    }

}