package com.losdelcallejon.gamesmachine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.losdelcallejon.gamesmachine.Screens.LoadingScreen;
import com.losdelcallejon.gamesmachine.Screens.LoginScreen;
import com.losdelcallejon.gamesmachine.Screens.MenuScreen;

import io.socket.client.IO;
import io.socket.client.Socket;

public class AbcGameMain extends Game {

	/// RECURSOS
	private AssetManager manager;
	public Socket socket;

	// SCREENS PRINCIPALES
	public LoadingScreen loadingScreen;
	public LoginScreen loginScreen;
	public MenuScreen menuScreen;

	//// IDENTIFICADOR DE USUARIO
	public int usuarioId;

	public AbcGameMain()
	{
		//Todo falta inicializar el socket
		//connectSocket();
	}
	public AssetManager getManager() {
		return manager;
	}

	public void setManager(AssetManager manager) {
		this.manager = manager;
	}
	private void connectSocket() {
		try {
			socket= IO.socket(Constants.SOCKET_URL);
			socket.connect();
		}catch (Exception ex)
		{
			System.out.printf(ex.getMessage());
		}
	}

	@Override
	public void create () {
		manager=new AssetManager();
		//Aqui se cargan las imagenes como en el siguiente ejemplo:
		// TODO cargar recursos
		manager.load("player.png", Texture.class);
		loadingScreen=new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	public void goToLogin() {
		loginScreen=new LoginScreen(this);
		menuScreen=new MenuScreen(this);
		setScreen(menuScreen);
	}
}