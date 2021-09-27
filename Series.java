import java.io.FileInputStream;
import java.io.*;



public class Series {
    private String nome, formato, duracao, paisdeorigem, idiomaoriginal, emissoradetv, transmissao, ndetemp, ndeep, ndeepi;
   
public Series(){
    this.nome= "";
    this.formato= "";
    this.duracao= "";
    this.paisdeorigem= "";
    this.idiomaoriginal= "";
    this.emissoradetv= "";
    this.transmissao= "";
    this.ndeep= "";
    this.ndetemp= "";
}

    public String getnome(){
        return this.nome;
    }
    public void setnome(String nome){
    this.nome= nome;
    }
    public void imprimir(){
        System.out.println(nome+" "+formato+" "+duracao+" "+paisdeorigem+" "+idiomaoriginal+" "+emissoradetv+" "+transmissao+" "+ndetemp+" "+ndeepi);
    }

    public Series clone(){
        Series clone= new Series();
        clone.setnome(this.nome);
        return clone;
    }
    

    public void ler(String endereco) throws Exception{
        InputStreamReader irs = new InputStreamReader(new FileInputStream(endereco));
        BufferedReader br = new BufferedReader(irs);
// nome

        while(!br.readLine().contains("infobox_v2"))
        ;
        br.readLine();
        this.nome=removetagsNovo(br.readLine());

// formato

        while(!br.readLine().contains("Formato"))
        ;
        this.formato=removetags(br.readLine());
        
//  duração

        while(!br.readLine().contains("Duração"))
        ;
        this.duracao=removetags(br.readLine());

// pais de origem#

while(!br.readLine().contains("País de origem"))
;
this.paisdeorigem=removetagsNovo(br.readLine());

// idioma original


while(!br.readLine().contains("Idioma original"))
;
this.idiomaoriginal=removetags(br.readLine());

// emissora de TV

while(!br.readLine().contains("Emissora de televisão original"))
;
this.emissoradetv=removetags(br.readLine());

// transmissão original#

while(!br.readLine().contains("Transmissão original"))
;
this.transmissao=removetagsNovo(br.readLine());

// numero de temporada

while(!br.readLine().contains("N.º de temporadas"))
;
this.ndetemp=removetags(br.readLine());

// numero de episodios(lista de episodios)

while(!br.readLine().contains("N.º de episódios"))
;
this.ndeep=removetagsNovo(br.readLine());
ndeepi=removedor(ndeep);

br.close();
    }

   String removedor(String line){
   String newline = "";
   int i=0;
   while(i<line.length()){
       if(line.charAt(i)<= '9' && '0' <= line.charAt(i)){
           newline+=line.charAt(i);}
        i++;
   }
  return newline;
   }

   
    public String removetags( String n){
        int i=0;
        String filme="";
        while(i<n.length()){
            if(n.charAt(i)=='<'){
                i++;
                while(n.charAt(i)!='>'){
                    i++;

                }
            }
            else{
                filme+=n.charAt(i);
            }
            i++;

        }

        return filme;
    }


    public String removetagsNovo( String n){
        int i=0;
        String filme="";
        while(i<n.length()){
            if(n.charAt(i)=='<'){
                i++;
                while(n.charAt(i)!='>'){
                    i++;

                }
            }
            else{
                filme+=n.charAt(i);
            }
            i++;

        }
        filme = filme.replaceAll("&nbsp;","");
        filme = filme.replaceAll("&#160;","");
        // filme = filme.replaceAll("(lista de episódios)","");


        return filme;
    }
    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }


    public static void main(String[] args)throws Exception {
    Series rei = new Series();
    String[] entrada = new String[1000];
    int numEntrada = 0;

    do {
       entrada[numEntrada] = MyIO.readLine();   
       if(isFim(entrada[numEntrada]) == false){ 
       rei.ler(entrada[numEntrada]);
           rei.imprimir();
       }

    } while (isFim(entrada[numEntrada++]) == false);
         numEntrada--;  
         

    }
}
