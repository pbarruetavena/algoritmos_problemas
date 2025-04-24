
package br.cefetmg.inf.listas.lista10.b1049;

import java.util.Scanner;

class Animal {
    private String tipo;
    
    protected Animal(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
}

class Vertebrado extends Animal {    
    protected Vertebrado(String tipo) {
        super(tipo);
    }
}

class Invertebrado extends Animal {    
    protected Invertebrado(String tipo) {
        super(tipo);
    }
}

class Ave extends Vertebrado {
    protected Ave(String tipo) {
        super(tipo);
    }
}

class Mamifero extends Vertebrado {    
    protected Mamifero(String tipo) {
        super(tipo);
    }
}

class Inseto extends Invertebrado {    
    protected Inseto(String tipo) {
        super(tipo);
    }
}

class Anelideo extends Invertebrado {    
    protected Anelideo(String tipo) {
        super(tipo);
    }
}

class Aguia extends Ave {
    public Aguia() {
        super("aguia");
    }
}

class Pomba extends Ave {
    public Pomba() {
        super("pomba");
    }
}

class Homem extends Mamifero {
    public Homem() {
        super("homem");
    }
}

class Vaca extends Mamifero {
    public Vaca() {
        super("vaca");
    }
}

class Pulga extends Inseto {
    public Pulga() {
        super("pulga");
    }
}

class Lagarta extends Inseto {
    public Lagarta() {
        super("lagarta");
    }
}

class Sanguessuga extends Anelideo {
    public Sanguessuga() {
        super("sanguessuga");
    }
}

class Minhoca extends Anelideo {
    public Minhoca() {
        super("minhoca");
    }
}

public class Main {
    public static void main(String args[]) {
        String cr1, cr2, cr3;
        Animal animal;
        
        Scanner in = new Scanner(System.in);
        cr1 = in.next();
        cr2 = in.next();
        cr3 = in.next();
        
        if(cr1.equals("vertebrado"))
            if(cr2.equals("ave"))
                if(cr3.equals("carnivoro")) 
                    animal = new Aguia();
                else 
                    animal = new Pomba();
            else 
                if(cr3.equals("onivoro"))
                    animal = new Homem();
                else
                    animal = new Vaca();
        else
            if(cr2.equals("inseto"))
                if(cr3.equals("hematofago"))
                    animal = new Pulga();
                else
                    animal = new Lagarta();
            else
                if(cr3.equals("hematofago"))
                    animal = new Sanguessuga();
                else
                    animal = new Minhoca();
        
        System.out.println(animal.getTipo());
        
    }
}
