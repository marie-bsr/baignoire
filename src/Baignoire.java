
public class Baignoire implements Runnable {

	private static int volumeMax;
	private int volume;
	int volumeFuite;

	public Baignoire(int paramvolumeMax, int paramvolumeFuite) {
		volumeMax = paramvolumeMax;
		volumeFuite = paramvolumeFuite;
		setVolume(0);
	}

	public int getVolume() {
		return volume;
	}

	public static int getVolumeMax() {
		return volumeMax;
	}

	//quand on implémente runnable, on peut mettre la méthode en private
	private int fuite() {
		while (volumeFuite > 0) {
//on entoure d'un bloc synchronized les lignes de codes qui doivent s'affectuer ensemble, si on ne la fait pas, une instruction venue d'un autre fichier peut s'insérer entre ces lignes
//on doit synchroniser le même objet des 2 cotés, ici baignoire
			synchronized (this) {
				if (getVolume() > 0) {
					setVolume(getVolume() - volumeFuite);
					//si le volume est négatif, on remplace par 0, sinon on garde le volume
					setVolume(getVolume() < 0 ? 0 : getVolume());
					System.out.println("ça fuit : Le volume de la baignoire est: " + getVolume() + " litres");

				} else {
					System.out.println("On colmate");
					volumeFuite -= 1;
				}
			}

			// on endort le thread pendant 1 milliseconde afin de permettre au thread qui n'a pas
			// la main de reprendre la main, car si on ne fait pas ça, celui qui a la main
			// en premier la garde tout le temps car on est dans la boucle while qui est trop rapide
			// il faut mettre le sleep dans un try catch car ça peut planter, si ça plante,
			// le try catch permet de ne pas interrompre le programme
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return getVolume();
	}

	@Override
	public void run() {

		fuite();

	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
