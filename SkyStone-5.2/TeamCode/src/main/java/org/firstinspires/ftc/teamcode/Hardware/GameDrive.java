package org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.*;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GameDrive {

    static public Bot bot;

    public GameDrive(Bot bot) {
        this.bot = bot;
    }

    public static void driveBot(double leftStickY,double rightStickX,double rightTrigger,double leftTrigger,double leftStickX,double rightStickY) {
        if(leftStickY > 0.15 || leftStickY < -0.15)
            bot.drivePower(leftStickY);
        else if(rightStickX > 0.15 || rightStickX < -0.15)
           bot.turnPower(rightStickX);
        else if (leftStickX > 0.15 || leftStickX < -0.15)
            bot.strafePower(leftStickX);
        else
            bot.stop();
    }
}