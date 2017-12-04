package mvc;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.model.Update;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;

public class Model implements Subject{
	
	private static final Update Update = null;


	private List<Observer> observers = new LinkedList<Observer>();
	
	
	private static Model uniqueInstance;


	
	private String tlocais;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	
	public Profissao[] gerarPerguntas(){
		Reader json;
		Gson gson = new Gson();
		
		
		try {
			json = new FileReader("C:/Users/aluno/teste.txt");
			Profissao[] profissao = gson.fromJson(json, Profissao[].class);
			return profissao;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

	
	public void  retornarLista(Update update){
			Profissao[] profissao = gerarPerguntas();
			ArrayList lista = new ArrayList();
			
			int x = 0;
			int y = 0;
			while(x < profissao.length){
				while(y < profissao[x].getHabilidades().size()){
					lista.add(profissao[x].getHabilidades().get(y).getName());
					y++;
				}
				y = 0;
				x++;
			}
			System.out.println(lista);
			this.notifyObservers(update.message().chat().id(), lista);
	
	}
	
	
	private void notifyObservers(Long chatId, ArrayList lista) {
			for(Observer observer:observers){
				observer.update(chatId, lista);
			}
	}

public void enviaResultado(Update update,ArrayList lista){
	System.out.println("Cheguei no resultado");
	ArrayList respostas = lista;
	String[] resp = (String[]) respostas.toArray(new String[respostas.size()]);
	
	  //Le o arquivo e compara as repostas
    ArrayList valores = new ArrayList();
    int maior = 0;
	
    Profissao[] profissao = gerarPerguntas();
    
    int x = 0;
	int y = 0;
	
	while(x < profissao.length){
		
		int cont = 0;
    	int a = 0;
		for(int i = 0; i < resp.length; i++){
			
			while(y < profissao[x].getHabilidades().size()){
				if(resp[i].equals(profissao[x].getHabilidades().get(y).getName())){
					
					a++;
        			System.out.println(profissao[x].getHabilidades().get(y).getName());
        			break;
				}
				
				y++;
			}
			
			y = 0;
		}
		System.out.println(a);
    	if(a == 0){
    		
    	}
    	else if(a == maior){
    		valores.add(profissao[x].getDescricao());
    		maior = a;
    	}
    	else if(a > maior){
    		valores.clear();
    		valores.add(profissao[x].getDescricao());
    		maior = a;
    	}
    	
		x++;
	}
	
	System.out.println(valores.size());
    
    Object[] profissoes = valores.toArray();
    
   tlocais = valores.toString();
    
    
	if(profissoes.length == 1){
		System.out.println("chamei a view");
    	this.notifyObservers(update.message().chat().id(), (String) profissoes[0]);
    	
    }else if(profissoes.length > 1){
    	for(int w = 0 ; w < profissoes.length; w++){
    		System.out.println("chamei a view");
    		this.notifyObservers(update.message().chat().id(), (String) profissoes[w]);
    		
    	}
    }
}
	
public void verificaLocalizacao(Update update,Object respostas){
		System.out.println("cheguei na verificar locaizao procurando repostas ");
		
		Profissao[] profissao = gerarPerguntas();
		
		
		
		Object[] locais = profissao[0].getLocal().toArray();
		int x = 0;
		while(locais.length > x){
			Double latitude = profissao[0].getLocal().get(x).getlatitude();
			Double longitude = profissao[0].getLocal().get(x).getLongitude();
			String title = profissao[0].getLocal().get(x).getTitle();
			String endereco = profissao[0].getLocal().get(x).getEndereco();
			this.notifyObserversLocais(update.message().chat().id(), latitude, longitude, title, endereco);
			x++;
		}
		
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(long chatId, String profissoes){
		for(Observer observer:observers){
			observer.update(chatId, profissoes);
		}
	}
	
	public void notifyObserversLocais(long chatId, Double latitude, Double longitude, String title, String endereco){
		for(Observer observer:observers){
			observer.updateLocais(chatId,latitude,longitude,title,endereco);
		}
	}

}
