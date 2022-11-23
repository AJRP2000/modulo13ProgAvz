package main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.*;

import canjeHandler.ICanjeHandler;
import clasesPrograma.Usuario;
import clasesVista.JFrameLogIn;
import exceptions.CanjeException;

public class Main{
	public static void main(String [] args) {
		JFrame frame = new JFrameLogIn();
		frame.setSize(800,650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(new JPanelABMCProgramas("Mostrar Programa"));
		frame.setVisible(true); 
		
	}
}


