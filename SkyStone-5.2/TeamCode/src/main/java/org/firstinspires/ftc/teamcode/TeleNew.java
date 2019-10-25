package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;

//import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Hardware.Bot;
import org.firstinspires.ftc.teamcode.Hardware.*;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleNew", group="Teleop")

//@Disabled
public class TeleNew extends OpMode {

    Bot bot = new Bot();
    int TankDrive = -1;
    /*
    fr = 1
    fl = 2
    bl = 3
    br = 4
     */

    @Override
    public void init() {
        bot.init(hardwareMap, telemetry, false);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

        GameDrive drive = new GameDrive(bot);
        double leftStickY = (double) -gamepad1.left_stick_y;
        double rightTrigger = (double) gamepad1.right_trigger;
        double leftTrigger = (double) gamepad1.left_trigger;
        double rightStickX = (double) gamepad1.right_stick_x;
        double rightStickY = (double) -gamepad1.right_stick_y;

        double leftTriggerIntake = (double) gamepad2.left_trigger;
        double rightTriggerIntake = (double) gamepad2.right_trigger;
        double leftStickServo = (double) gamepad2.left_stick_x;
        double rightStickServo = (double) gamepad2.right_stick_x;

        drive.driveBot(leftStickY, rightStickX, rightTrigger, leftTrigger, 0.0, 0.0);
        if(leftTriggerIntake > 0.15)
            bot.LI.setPower(leftTriggerIntake);
        else
            bot.LI.setPower(0.0);
        if(rightTriggerIntake > 0.15)
            bot.RI.setPower(rightTriggerIntake);
        else
            bot.RI.setPower(0.0);
        if(leftStickServo > 0.15 || leftStickServo < -0.15) {
            if(leftStickServo < 0.5 || leftStickServo > 0.5)
                bot.LC.setPower(leftStickServo);
            else if(leftStickServo < 0)
                bot.LC.setPower(-0.5);
            else
                bot.LC.setPower(0.5);
        }
        if(rightStickServo > 0.15 || rightStickServo < -0.15) {
            if(rightStickServo < 0.5 || rightStickServo > 0.5)
                bot.RC.setPower(rightStickServo);
            else if(rightStickServo < 0)
                bot.RC.setPower(-0.5);
            else
                bot.RC.setPower(0.5);
        }
        if(gamepad2.right_bumper)
            bot.lift(1.0);
        if(gamepad2.left_bumper)
            bot.lift(0.0);


    }

    @Override
    public void stop() {
        bot.stop();

    }
}