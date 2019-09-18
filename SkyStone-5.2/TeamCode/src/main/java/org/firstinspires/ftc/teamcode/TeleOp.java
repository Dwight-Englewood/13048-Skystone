package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;

//import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.Bot;


@TeleOp(name = "TeleOp1", group = "Teleop")
@Disabled
public class teleOp extends OpMode {

    Bot bot = new Bot();
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

//        double botTheta = bot.imu.getGyroRotation(AngleUnit.RADIANS);
        //The readings from the gyro are different from the reading needed for the field centric code, so we apply a function to fix it
//        botTheta = (botTheta < 0) ? -botTheta : 2 * Math.PI - botTheta;
//        botTheta = botTheta;
        double lsx = -gamepad1.left_stick_x;
        double lsy = -gamepad1.left_stick_y;
        double theta = gamepad1.right_stick_x / 2;
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