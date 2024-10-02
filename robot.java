// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_robotDrive;
  private XboxController controller;

  private final CANSparkMax m_leftMotor = new CANSparkMax(3, MotorType.kBrushed);
  private final CANSparkMax m_rightMotor = new CANSparkMax(1, MotorType.kBrushed);

  private final CANSparkMax m_leftMotor1 = new CANSparkMax(4, MotorType.kBrushed);
  private final CANSparkMax m_rightMotor1 = new CANSparkMax(2, MotorType.kBrushed);

  @Override
  public void robotInit() {

    m_rightMotor.restoreFactoryDefaults();
    m_leftMotor.restoreFactoryDefaults();
    m_leftMotor1.restoreFactoryDefaults();
    m_rightMotor1.restoreFactoryDefaults();

    m_leftMotor1.follow(m_leftMotor);
    m_rightMotor1.follow(m_rightMotor, true); 

    SendableRegistry.addChild(m_robotDrive, m_leftMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

      


    // m_rightMotor.setInverted(false);
    // m_leftMotor.setInverted(false);
    // m_rightMotor1.setInverted(false);
    // m_leftMotor1.setInverted(false);

    m_rightMotor.burnFlash();
    m_leftMotor.burnFlash();
    m_rightMotor1.burnFlash();
    m_leftMotor1.burnFlash();

    m_robotDrive = new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
    controller = new XboxController(0);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(controller.getLeftY(), controller.getRightY());
    System.out.println(m_rightMotor1.getAppliedOutput());
    System.out.println(m_leftMotor1.getAppliedOutput());


  }
}
