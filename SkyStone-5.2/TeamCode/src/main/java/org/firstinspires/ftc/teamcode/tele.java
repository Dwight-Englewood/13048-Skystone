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

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group="Teleop")

//@Disabled
public class tele extends OpMode {

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

//        double lsx = gamepad1.left_stick_x;
        GameDrive drive = new GameDrive(bot);
        TankDrive tank = new TankDrive(bot);
        double leftStickY = (double) -gamepad1.left_stick_y;
        double rightTrigger = (double) gamepad1.right_trigger;
        double leftTrigger = (double) gamepad1.left_trigger;
        double rightStickX = (double) gamepad1.right_stick_x;
        double rightStickY = (double) -gamepad1.right_stick_y;
        double leftStickX = (double) gamepad1.left_stick_x;
        double leftTrigger2 = (double) gamepad2.left_trigger;
        double rightTrigger2 = (double) gamepad2.right_trigger;
        if(!gamepad1.right_bumper) {
            drive.driveBot(leftStickY, rightStickX, rightTrigger, leftTrigger, 0.0, 0.0);
        }
        else if(rightStickX < -0.15 && gamepad1.right_bumper) {
            bot.turnPower(-0.10);
        }
        else if(rightStickX > 0.15 && gamepad1.right_bumper){
            bot.turnPower(0.10);
        }
        else{
            bot.stop();
        }
        if(gamepad2.dpad_up)
            bot.lift.setPower(1);
        else if(gamepad2.dpad_down)
            bot.lift.setPower(-1);
        else
            bot.lift.setPower(0);
        if(gamepad2.a) {
            bot.claw.setPosition(0.1);
            telemetry.addData(">", "0.1");
            telemetry.update();
        }
        if(gamepad2.b) {
            bot.claw.setPosition(0.9);
            telemetry.addData(">", "0.9");
            telemetry.update();
        }
        if(gamepad2.x){
                bot.rHook.setPosition(1.0);
                bot.lHook.setPosition(0.15);

        }
        if(gamepad2.y){
            bot.rHook.setPosition(0.15);
            bot.lHook.setPosition(1.0);
        }
//        if(gamepad2.right_stick_y > 0.15)
//            bot.FR.setPower((double) gamepad2.right_stick_y);
//        else
//            bot.FR.setPower(0.0);
//        if(gamepad2.left_stick_y > 0.15)
//            bot.FL.setPower((double) gamepad2.left_stick_y);
//        else
//            bot.FL.setPower(0.0);
//        double botTheta = bot.imu.getGyroRotation(AngleUnit.RADIANS);
        //The readings from the gyro are different from the reading needed for the field centric code, so we apply a function to fix it
//        botTheta = (botTheta < 0) ? -botTheta : 2 * Math.PI - botTheta;
//        botTheta = botTheta;
//        double lsx = -gamepad1.left_stick_x;
//        double lsy = -gamepad1.left_stick_y;

//        double theta = gamepad1.right_stick_x / 2;
//        SimpleMatrix powVector = bot.driveTrain.drive(lsx, lsy, theta, botTheta);



//        if (gamepad1.dpad_up) {
//            bot.dumperPivot.variableSafe(.5);
//        } else if (gamepad1.dpad_down) {
//            bot.dumperPivot.variableSafe(-.3);
//        } else {
//            bot.dumperPivot.variableSafe(0);
//        }

//        if (gamepad1.x) {
//            bot.dumperPivot.pivotScore();
//        } else {
//            bot.dumperPivot.pivotNotScore();
//        }

//        if (gamepad1.dpad_right) {
//            bot.intakeSlides.variableMove(.5);
//        } else if (gamepad1.dpad_left) {
//            bot.intakeSlides.variableMove(-.5);
//        } else {
//            bot.intakeSlides.variableMove(0);
//        }

//        if (gamepad1.left_trigger > .5) {
//            bot.intakeSlides.outtake();
//        } else if (gamepad1.right_trigger > .5) {
//            bot.intakeSlides.intake();
//        } else {
//            bot.intakeSlides.notake();
//        }

//        if (gamepad2.a) {
//            bot.intakeSlides.pivotDown();
//        } else if (gamepad2.b) {
//            bot.intakeSlides.pivotUp();
//        } else if (gamepad2.y) {
//            bot.intakeSlides.pivotMiddle();
//        }

    }

    @Override
    public void stop() {
        bot.stop();

    }
}