package mvc;


public interface Subject {
	
	public void registerObserver(Observer observer);
	
	public void notifyObservers(long chatId, String profissoes);
	
	public void notifyObserversLocais(long chatId, Double latitude, Double longitude, String title, String endereco);

}
