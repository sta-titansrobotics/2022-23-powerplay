package team_19446;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class testpid extends LinearOpMode {    
   

    public void setVelF(double vel, DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB) {
        LF.setVelocity(vel);
        LB.setVelocity(vel);
        RF.setVelocity(vel);
        RB.setVelocity(vel);
    }

    public void setVelS(double vel, DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB) {
        LF.setVelocity(-vel);
        LB.setVelocity(vel);
        RF.setVelocity(vel);
        RB.setVelocity(-vel);
    }



    public void arm() { //gonna make this later

    }


    public void PID (direction direction, double CM, double coefficientproportional, double coefficientderivative) {
        double Cd = coefficientderivative; // pid coefficient for derivative control
        double Cp = coefficientproportional; // pid coefficient for proportional control
        double d = 0; // derivative
        double p = 0; // proportional

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double lf = LF.getCurrentPosition();
        double lb = LB.getCurrentPosition();
        double rf = RF.getCurrentPosition();
        double rb = RB.getCurrentPosition();
        double lastError = 0;

        double tick;
        double def = 0;

        if (direction == direction.FRONT) {

            tick = CM * forwardticks;

            def = tick/3;

            while (lf != tick || lb != tick || rf != tick || rb != tick) {
                PIDTimer.reset(); //resets the timer
                
                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;

                setVelF(p + d);

                lastError = error;

            }


        }

        else if (direction == direction.BACK) {

            tick = CM * forwardticks;

            def = tick/3;

            while (lf != tick || lb != tick || rf != tick || rb != tick) {
                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;

                setVelF(-1 *(p + d));

                lastError = error;

            }

        }
        else if (direction == direction.LEFT) {

            tick = CM * strafeticks;
            def = tick/3;

            while (lf != -tick || lb != tick || rf != tick || rb != -tick) {

                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * Cp * def;

                d = derivative * Cd * def;

                setVelS(p + d);

                lastError = error;

            }

        }

        else if (direction == direction.RIGHT) {

            tick = CM * strafeticks;
            def = tick/3;

            while (lf != tick || lb != -tick || rf != -tick || rb != tick) {
                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * Cp;

                d = derivative * def * Cd;

                setVelS((p + d)*-1);

                lastError = error;

            }

        }
    }
}