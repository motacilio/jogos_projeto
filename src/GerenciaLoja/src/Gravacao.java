import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Gravacao {

    public static void main(String[] args) throws IOException {
        //Gravando Vendedores
        gravaVendedor(new Vendedor("João", "9678", 2000));
        gravaVendedor(new Vendedor("Ana", "1234", 2500));
        gravaVendedor(new Vendedor("Bruno", "5678", 3000));
        gravaVendedor(new Vendedor("Carlos", "9101", 2200));
        gravaVendedor(new Vendedor("Diana", "1123", 2700));
        gravaVendedor(new Vendedor("Eduardo", "4567", 3100));
        

        //Gravando Jogos Digitais
        gravaJogoDig(new Digital("Minecraft", 15, 20, "Aventura", "Mojang", "PC", "Online"));
        gravaJogoDig(new Digital("The Legend of Zelda: Breath of the Wild", 12, 59.99, "Aventura", "Nintendo", "Nintendo Switch", "Offline"));
        gravaJogoDig(new Digital("Cyberpunk 2077", 14, 49.99, "RPG", "CD Projekt", "PC", "Offline"));
        gravaJogoDig(new Digital("God of War", 10, 39.99, "Ação/Aventura", "Santa Monica Studio", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Spider-Man: Miles Morales", 8, 49.99, "Ação/Aventura", "Insomniac Games", "PlayStation 5", "Offline"));
        gravaJogoDig(new Digital("Final Fantasy VII Remake", 9, 59.99, "RPG", "Square Enix", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Halo Infinite", 15, 59.99, "FPS", "343 Industries", "Xbox Series X", "Online"));
        gravaJogoDig(new Digital("Resident Evil Village", 7, 59.99, "Survival Horror", "Capcom", "PC", "Offline"));
        gravaJogoDig(new Digital("Assassin's Creed Valhalla", 11, 49.99, "Ação/RPG", "Ubisoft", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Ghost of Tsushima", 6, 59.99, "Ação/Aventura", "Sucker Punch Productions", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Call of Duty: Modern Warfare", 20, 59.99, "FPS", "Infinity Ward", "PC", "Online"));
        gravaJogoDig(new Digital("Horizon Zero Dawn", 13, 29.99, "Ação/RPG", "Guerrilla Games", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Grand Theft Auto V", 18, 29.99, "Ação/Aventura", "Rockstar Games", "PlayStation 4", "Online"));
        gravaJogoDig(new Digital("Mario Kart 8 Deluxe", 25, 59.99, "Corrida", "Nintendo", "Nintendo Switch", "Online"));
        gravaJogoDig(new Digital("The Elder Scrolls V: Skyrim", 10, 19.99, "RPG", "Bethesda Game Studios", "PC", "Offline"));
        gravaJogoDig(new Digital("Uncharted 4: A Thief's End", 7, 19.99, "Ação/Aventura", "Naughty Dog", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Doom Eternal", 12, 39.99, "FPS", "id Software", "PC", "Offline"));
        gravaJogoDig(new Digital("Persona 5", 8, 49.99, "RPG", "Atlus", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Dark Souls III", 6, 59.99, "RPG", "FromSoftware", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("The Last of Us Part II", 5, 59.99, "Ação/Aventura", "Naughty Dog", "PlayStation 4", "Offline"));
        gravaJogoDig(new Digital("Sekiro: Shadows Die Twice", 4, 59.99, "Ação/Aventura", "FromSoftware", "PC", "Offline"));

        //Gravando Jogos de Tabuleiro
        gravaJogoTab(new Tabuleiro("Catan", 15, 44.99, "Estratégia", "Kosmos", 4));
        gravaJogoTab(new Tabuleiro("Carcassonne", 12, 34.99, "Estratégia", "Z-Man Games", 5));
        gravaJogoTab(new Tabuleiro("Ticket to Ride", 18, 49.99, "Família", "Days of Wonder", 5));
        gravaJogoTab(new Tabuleiro("Pandemic", 10, 39.99, "Cooperativo", "Z-Man Games", 4));
        gravaJogoTab(new Tabuleiro("Azul", 20, 39.99, "Abstrato", "Next Move Games", 4));
        gravaJogoTab(new Tabuleiro("7 Wonders", 8, 49.99, "Estratégia", "Repos Production", 7));
        gravaJogoTab(new Tabuleiro("Dominion", 14, 44.99, "Estratégia", "Rio Grande Games", 4));
        gravaJogoTab(new Tabuleiro("Splendor", 16, 34.99, "Estratégia", "Space Cowboys", 4));
        gravaJogoTab(new Tabuleiro("Agricola", 6, 59.99, "Estratégia", "Z-Man Games", 5));
        gravaJogoTab(new Tabuleiro("Terraforming Mars", 9, 69.99, "Estratégia", "Stronghold Games", 5));
        gravaJogoTab(new Tabuleiro("Gloomhaven", 5, 139.99, "Cooperativo", "Cephalofair Games", 4));
        gravaJogoTab(new Tabuleiro("Scythe", 7, 79.99, "Estratégia", "Stonemaier Games", 7));
        gravaJogoTab(new Tabuleiro("Codenames", 25, 19.99, "Partida", "Czech Games Edition", 8));
        gravaJogoTab(new Tabuleiro("Wingspan", 11, 54.99, "Estratégia", "Stonemaier Games", 5));
        gravaJogoTab(new Tabuleiro("The Castles of Burgundy", 10, 39.99, "Estratégia", "Ravensburger", 4));
        gravaJogoTab(new Tabuleiro("Blood Rage", 6, 79.99, "Estratégia", "CMON", 5));
        gravaJogoTab(new Tabuleiro("Clank!", 13, 59.99, "Aventura", "Renegade Game Studios", 4));
        gravaJogoTab(new Tabuleiro("Root", 9, 59.99, "Estratégia", "Leder Games", 4));
        gravaJogoTab(new Tabuleiro("Spirit Island", 7, 79.99, "Cooperativo", "Greater Than Games", 4));
        gravaJogoTab(new Tabuleiro("Everdell", 8, 59.99, "Estratégia", "Starling Games", 4));



    }

    public static void gravaJogoTab(Tabuleiro jogo) throws IOException{
        try{
            boolean append = new File("EstoqueTab.txt").exists();
            FileOutputStream fx = new FileOutputStream("EstoqueTab.txt", true);
            ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

            ox.writeObject(jogo);
            ox.flush();
            ox.close();
            fx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void gravaJogoDig(Digital jogo) throws IOException{
        try{
            boolean append = new File("EstoqueDig.txt").exists();
            FileOutputStream fx = new FileOutputStream("EstoqueDig.txt", true);
            ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

            ox.writeObject(jogo);
            ox.flush();
            ox.close();
            fx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void gravaGerente(Gerente gerente) throws IOException {
        FileOutputStream fx = new FileOutputStream("Gerente.txt");
        ObjectOutputStream ox = new ObjectOutputStream(fx);

        ox.writeObject(gerente);

        ox.close();
        fx.close();
    }

    public static void gravaVendedor(Vendedor vendedor) throws IOException {
        try{
            boolean append = new File("vend.txt").exists();
            FileOutputStream fx = new FileOutputStream("vend.txt", true);
            ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

            ox.writeObject(vendedor);
            ox.flush();
            ox.close();
            fx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
    
}
