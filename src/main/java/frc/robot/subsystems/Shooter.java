package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.lib.drivers.LazyTalonSRX;
import frc.lib.drivers.PheonixUtil;
import frc.lib.drivers.TalonSRXFactory;
import frc.lib.drivers.TalonSRXUtil;
import frc.robot.Constants;
import frc.robot.Ports;

public class Shooter extends Subsystem
{
	private static Shooter mInstance = null;

	public static Shooter getInstance()
	{
		if(mInstance == null)
		{
			mInstance = new Shooter();
		}
		return mInstance;
	}

	// hardware
	private final LazyTalonSRX mShooterMotor;

	private void configureShooterMotor(LazyTalonSRX talon, InvertType inversion)
	{
		talon.setInverted(inversion);
		PheonixUtil.checkError(talon.configVoltageCompSaturation(12.0, Constants.kTimeOutMs),
			talon.getName() + " failed to set voltage compensation", true);
			talon.enableVoltageCompensation(true);

			talon.setNeutralMode(NeutralMode.Coast);

			TalonSRXUtil.setCurrentLimit(talon, 25);
	}

	private Shooter()
	{
		mShooterMotor = TalonSRXFactory.createDefaultTalon("Shooter Motor", Ports.SHOOTER_ID);
		configureShooterMotor(mShooterMotor, InvertType.None);
	}

	public void shoot()
	{
		mShooterMotor.set(ControlMode.PercentOutput, Constants.shooterPercentage);
	}

	public boolean checkSystem()
	{
		return false; 
	}

	public void stop()
	{
		mShooterMotor.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public void outputTelemetry()
	{
	
	}
}
