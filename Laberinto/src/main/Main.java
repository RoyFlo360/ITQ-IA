package main;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import test.Principal;
import test.Util;

public class Main
{
	public static void main(String [] args)
	{
		try
		{
			Util.resource=ResourceBundle.getBundle("resources.textos", Locale.getDefault());
		}
		catch(MissingResourceException mre)
		{
			Locale.setDefault(new Locale("en"));
			Util.resource=ResourceBundle.getBundle("resources.textos", Locale.getDefault());
		}
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new Principal();
			}
		});
	}
}
