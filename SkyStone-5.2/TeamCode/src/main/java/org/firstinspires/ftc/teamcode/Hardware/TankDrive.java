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
import java.lang.Math;

public class TankDrive {

    public Bot bot;

    public TankDrive(Bot bot) {
        this.bot = bot;

        public static void Drive ( double leftStickY, double rightStickX, double rightTrigger,
        double leftTrigger, double rightStickY, double leftStickX){
            bot.drivePower(leftStickY);
            bot.drivePower(rightStickX);
            double StickyPressure = .5;

            if (leftStickY > Math.abs(StickyPressure) && rightStickY > Math.abs(StickyPressure)) {
                bot.drivePower((leftStickY + rightStickY) / 2.00);
            }
            if (leftStickX > Math.abs(StickyPressure) && rightStickX > Math.abs(StickyPressure)) {
                bot.strafePower((leftStickX + rightStickX) / 2.00);
            })


        }
    }
}