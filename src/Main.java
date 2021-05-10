
public class Main {
	
	public static void main(String[] args) {
		Baignoire baignoire = new Baignoire(1000, 10);
		Robinet robinet = new Robinet(baignoire, 30);
		Thread threadBaignoire = new Thread(baignoire);
		Thread threadRobinet = new Thread(robinet);
		
		threadBaignoire.start();
		threadRobinet.start();

	
	}
	
}

//quand il y a plusieurs threads, les 2 ne s'effectuent pas en même temps mais l'un après l'autre.'
//cela peut entrainer un "decoupage" de lignes de code, un "insert" d'une ligne non souhaitée
// 2 fils d'exécutions qui manipulent le même objet peuvent entrainer des choses non voulues
//pour éviter ça, on met un verou cad un bloc synchronized sur l'objet à vérouiller'