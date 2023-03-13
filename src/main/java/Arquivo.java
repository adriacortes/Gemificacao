import org.adria.TipoPonto;
import org.adria.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Arquivo {

    File arq;
    FileOutputStream gravarArq;
    FileReader lerArquivo;
    BufferedReader lerLinhaArquivo;

    BufferedOutputStream gravarDados;

    String filePath = "pontos_usuarios.txt";

    public Arquivo() throws IOException {

        this.arq = new File(filePath);
        this.gravarArq = new FileOutputStream(arq, true);
        this.gravarDados = new BufferedOutputStream(gravarArq);
        this.lerArquivo = new FileReader(arq);
        this.lerLinhaArquivo = new BufferedReader(lerArquivo);

    }

    public String gravarDadosEmArquivo(Usuario usuario) {
        try {
            gravarDados.write("\n".getBytes());
            gravarDados.write(usuario.getNome().getBytes());
            gravarDados.write(";".getBytes());
            extraindoPontosMap(usuario.retornaMapComPontos());
            gravarDados.flush();
            return "Gravado com sucesso";
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    private void extraindoPontosMap(Map<Enum, Integer> mapPontosUsuario) throws IOException {
        for (Map.Entry<Enum, Integer> enumIntegerEntry : mapPontosUsuario.entrySet()) {
            gravarDados.write(enumIntegerEntry.getKey().toString().getBytes());
            gravarDados.write(";".getBytes());
            gravarDados.write(enumIntegerEntry.getValue().toString().getBytes());
            gravarDados.write(";".getBytes());
        }
    }

    public String buscarUsuario(String nome) throws IOException {
        String linhaArquivo;
        String[] pegandoDadosSeparadosLinha ;

        while ((linhaArquivo = lerLinhaArquivo.readLine()) != null) {
            pegandoDadosSeparadosLinha = linhaArquivo.split(";");

            if (pegandoDadosSeparadosLinha[0].equals(nome)){
                return linhaArquivo;
            }
        }

        return null;

    }

    public Usuario buscarPontosDoUsuarioPorTipo(String nome, TipoPonto tipoPonto) throws IOException {

        String linhaArquivo;
        String[] pegandoDadosSeparadosLinha ;
        Usuario usuario = new Usuario();

        while ((linhaArquivo = lerLinhaArquivo.readLine()) != null) {
            pegandoDadosSeparadosLinha = linhaArquivo.split(";");

            if (pegandoDadosSeparadosLinha[0].equals(nome)){
                usuario = Usuario.criaApartirDeUm(pegandoDadosSeparadosLinha,tipoPonto);
                return usuario;
            }

        }

        return usuario;
    }
}
