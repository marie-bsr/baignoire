
public class Robinet implements Runnable {

	private final Baignoire baignoire;
	private int volumeDebite;

	public Robinet(Baignoire baignoire,int volumeDebite) {
		this.baignoire = baignoire;
		this.volumeDebite = volumeDebite;
	}

	
//quand on implémente runnable, on peut mettre la méthode en private
	private void debite() {
		while (baignoire.getVolumeMax() > baignoire.getVolume()) {
			//on entoure d'un bloc synchronized les lignes de codes qui doivent s'affectuer ensemble, si on ne la fait pas, une instruction venue d'un autre fichier peut s'insérer entre ces lignes
			//on doit synchroniser le même objet des 2 cotés, ici baignoire
			synchronized (baignoire) {
				baignoire.setVolume(baignoire.getVolume() + volumeDebite);
						
				System.out.println("ça se remplit : Le volume de la baignoire est: " + baignoire.getVolume() +" litres");
			
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



	@Override
	public void run() {
		debite();
		
	}

}
