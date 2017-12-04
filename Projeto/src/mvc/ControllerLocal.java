package mvc;

import java.util.ArrayList;

import com.pengrad.telegrambot.model.Update;

public class ControllerLocal implements ControllerLocation {

	private Model model;
	private View view;
	
	public ControllerLocal(Model model2, View view2) {
		this.model = model2; //connection Controller -> Model
		this.view = view2; //connection Controller -> View
	}

	public void search(Update update, Object respostas) {
		System.out.println("Cheguei no controller vou chamar o model");
		view.sendTypingMessage(update);
		model.verificaLocalizacao(update, respostas);
	}

}
