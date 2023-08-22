
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable; //classe biblioteca java, observadora, nada mais que isto.

/**
 *
 * @author Joice
 */
public class Jogo extends Observable {
    
    private int[][] solucao;       // Generated solution.
    private int[][] jogo;           // Generated game with user input.
    private boolean[][] check;      // Holder for checking validity of game.
    private int numeroSelecionado;     // Selected number by user.
    private boolean ajuda; 
    
    
    //CONSTRUTOR DA CLASSE
    public Jogo() {
        novoJogo();
        check = new boolean[9][9];
        ajuda = true;
    }
    
    /*
    * Função novoJogo
    * Esta função gera um novo jogo aleatorio;
    */
    public void novoJogo() {
        solucao = geradorDeSolucoes(new int[9][9], 0);
        jogo = gerarJogo(copiar(solucao));
        setChanged(); //Limpa a lista de observadores para que este objeto não tenha mais observadores. Setando estado interno do objeto para true
        notifyObservers(Botoes.NOVO_JOGO); // acionado apos o metodo SetChanged() chamando o metodo Update para passar os dados alterados.
    }
    
    /*
    * Função checkJogo
    * Verifica a entrada do usuario e a adiciona na matriz de verificação
    */
    public void checkJogo() {
        numeroSelecionado = 0; // nenhum numero é selecionado
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++){
                check[y][x] = (jogo[y][x] == solucao[y][x]); // Comparando cada campo do jogo com a solução e a armazena em uma matriz booleana;
            }
        }
        setChanged(); //Limpa a lista de observadores para que este objeto não tenha mais observadores. Setando estado interno do objeto para true
        notifyObservers(Botoes.CHECK); // acionado apos o metodo SetChanged() chamando o metodo Update para passar os dados alterados.
    }
    
    /*
    * Função Ajuda
    * Função retorna true ou falso caso queira ajuda.
    */
    public void setAjuda(boolean ajuda) {
        this.ajuda = ajuda;
        setChanged(); //Limpa a lista de observadores para que este objeto não tenha mais observadores. Setando estado interno do objeto para true
        notifyObservers(Botoes.AJUDA);// acionado apos o metodo SetChanged() chamando o metodo Update para passar os dados alterados.
    }    

    /*
    * Função setNumeroSelecionado
    * Função para setar a variavel numeroSelecionado para o valor de entrada do usuario;
    * variavel numeroSelecionado valor selecionado pelo usuario;
    */
    public void setNumeroSelecionado(int numeroSelecionado) {
        this.numeroSelecionado = numeroSelecionado;
        setChanged(); //Limpa a lista de observadores para que este objeto não tenha mais observadores. Setando estado interno do objeto para true
        notifyObservers(Botoes.SELECIONA_NUMERO);// acionado apos o metodo SetChanged() chamando o metodo Update para passar os dados alterados.
    }
    
    /*
    * Retorna o numero selecionado pelo usuario
    */
    public int getNumeroSelecionado() {
        return numeroSelecionado;
    }
    
    /*
    * Retorna se a ajuda esta ou não acionada.
    */
    public boolean isAjuda() {
        return ajuda;
    }
    
    /*
    * Função isSelecionaNumeroCandidato
    * Função retorna True se o numero selecionado é candidato da posição
    * Variavel X e Y posiçoes no jogo
    */
    public boolean isSelecionaNumeroCandidato(int x, int y) {
        return jogo[y][x] == 0 && xPossivel(jogo, y, numeroSelecionado)
                && yPossivel(jogo, x, numeroSelecionado) && possivelBloco(jogo, x, y, numeroSelecionado);
    }
    
    /*
    * Função setNumero
    * Função setar conjuto de numeros em certa posição do jogo
    * Variavel X e Y posiçoes no jogo
    * Variavel numero valor a ser definido na posição
    */
    public void setNumero(int x, int y, int numero) {
        jogo[y][x] = numero;
    }

    /*
    * Função getNumero
    * Retorne valor da posição
    * Variaveis X e Y posição no jogo;
    */
    public int getNumero(int x, int y) {
        return jogo[y][x];
    }
    
    /*
    * Função isCheckValido
    * Retorna de entrada é valida em determinada posição;
    * Variaveis X e Y posição no jogo;
    */
    public boolean isCheckValido(int x, int y) {
        return check[y][x];
    }

    /*
    * Função GerarJogo
    * Função feita para gerar o jogo, retornando o mesmo jogo gerado.
    * Variavel jogo a ser gerado.
    */
    private int[][] gerarJogo(int[][] jogo) {
        List<Integer> posicoes = new ArrayList<Integer>(); // Array com todas as posiçoes possiveis
        for (int i = 0; i < 81; i++){ // as 81 posiçoes do tabuleiro
            posicoes.add(i);// adiciona indice na posição
        }
        Collections.shuffle(posicoes); // embaralha array
        return gerarJogo(jogo, posicoes); //retorna ao metodo abaixo sera explicado 
    }
    
    /*
    * Função gerarJogo
    * Metodo para remover número de uma posição, caso o jogo não fique mais valido apos a ação
    * então sera trazido ao estado anterior
    * Variavel jogo sera gerado
    * Array posicoes é a lista de posiçoes a serem removidas 
    */ 
    private int[][] gerarJogo(int[][] jogo, List<Integer> posicoes) {
        while (posicoes.size() > 0) {
            int posicao = posicoes.remove(0); //Posição tetirada da lista e add em uma variavel
            int x = posicao % 9; 
            int y = posicao / 9;
            int temp = jogo[y][x]; // posição armazenada na variavel temp
            jogo[y][x] = 0;  // definimos valor para zero

            if (!isValido(jogo)){ // Verifica se o jogo ainda é valido para continuar, se não for devolve a posição anterior
                jogo[y][x] = temp;
            }
        }

        return jogo;
    }
    
    /*
    *Função geradorDeSolucoes
    * jogo é matriz 9x9
    * indice representa o indice atual ao qual o usuario passa
    */
    private int[][] geradorDeSolucoes(int[][] jogo, int indice) {
        
        if (indice > 80) //Quantidade maxima de preenchimento dos quadrados, 0-80 = 81 posiçoes
            return jogo;

        int x = indice % 9; //movimentação no eixo X, ao qual o x é o resto da divisão do indice pela quantidade de campos maximo na linha no caso 9; 
        int y = indice / 9; //movimentação no eixo Y, é o valor inteiro da divisão do indice pela contagem de campos;

        List<Integer> arrayDeNumeros = new ArrayList<Integer>(); // 1 a 9, numeros possiveis para add
        for (int i = 1; i <= 9; i++){
            arrayDeNumeros.add(i); //adiciona o indice i em cada posição do array 1-9
            Collections.shuffle(arrayDeNumeros); // Shuffle metodo da coleção java usado para embaralhar elemento, no caso a array;
        } //É necessario que embaralhe o vetor para ter mais de um jogo.

        while (arrayDeNumeros.size() > 0) { //Enquanto tiver numeros no vetor, o mesmo deve continuar executando
            int numero = getProximoNumero(jogo, x, y, arrayDeNumeros);
            if (numero == -1){ // Caso finalize o array que ficara com os numeros, retorna null e sai do while
                return null;
            }
            jogo[y][x] = numero; //numero na atual posição
            int[][] aux = geradorDeSolucoes(jogo, indice + 1); // recursividade e aumento de indice, armazenando o retorno em uma variavel
            if (aux != null){ // campo vazio
                return aux;
            }
            jogo[y][x] = 0;
        }
        return null; //questão de escrita
    }
    
    /*
    * Função getProximoNumero
    * Função retorna o proximo numero possivel na lista para posição ou -1 para lista vazia
    * variavel jogo para conferir se esta certo
    * variavel X e Y posiçoes em jogo
    * variavel numeros lista de numeros restantes.
    */
    private int getProximoNumero(int[][] jogo, int x, int y, List<Integer> numeros) {
    while (numeros.size() > 0) {//Se a lista estiver vazia então o valor sera -1 não entrando no while
        int numero = numeros.remove(0); //remove metodo do list para remover valor da lista
        if (xPossivel(jogo, y, numero) //verifica possivel em X
               && yPossivel(jogo, x, numero) // verifica se possivel em Y
               && possivelBloco(jogo, x, y, numero)){ // verifica se é possivel em bloco
            return numero;
        }
    }
    return -1;
    }
    
    /*
    *Função xPossivel
    * Função booleana que retorna verdadeiro se numero for candidato do eixo X;
    * variavel jogo para conferir se esta certo
    * variavel Y posição para ser verificada
    * variavel numero mesma função do jogo
    */
    private boolean xPossivel(int[][] jogo, int y, int numero) {
        for (int x = 0; x < 9; x++) {
            if (jogo[y][x] == numero){
                return false;
            }
        }
        return true;
    }

    /*
    *Função xPossivel
    * Função booleana que retorna verdadeiro se numero for candidato do eixo Y;
    * variavel jogo para conferir se esta certo
    * variavel X posição para ser verificada
    * variavel numero mesma função do jogo
    */
    private boolean yPossivel(int[][] jogo, int x, int numero) {
        for (int y = 0; y < 9; y++) {
            if (jogo[y][x] == numero)
                return false;
        }
        return true;
    }
        
    /*
    * Função possivelBloco
    * Função booleana que retorna verdadeiro se numero é candidato ao bloco.
    * variavel jogo para conferir se esta certo.
    * variavel X e Y posição para ser verificada
    * variavel numero mesma função do jogo
    */
    private boolean possivelBloco(int[][] game, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (game[yy][xx] == number)
                    return false;
            }
        }
        return true;
    }
    
    /*
    * Função isValido
    * Função booleana para verificar se o jogo é valido
    * Variavel jogo para conferir a solução
    */
    private boolean isValido(int[][] jogo) {
        return isValido(jogo, 0, new int[] { 0 });
    }
    /*
    UM JOGO VALIDO CADA LINHA, COLUNA E BLOCO 3X3 POSSUE NUMEROS DE 1 A 9
    ALEM DISTO DEVE SE TER UMA SOLUÇÃO EXISTENTE. PARA QUE ISTO OCORRA
    TODOS OS CAMPOS SÃO PREENCHIDOS COM O VALOR VALIDO. 
    */
    
    /*
    * Funçao isValido
    * Utiliza a função a cima para verificar se jogo é valido, podendo então 
    * haver apenas uma solução de jogo.
    * Variavel jogo para conferir;
    * Variavel indice verificar o indice atual.
    * Array numeroDeSolucoes numero de soluções encontradas, passada em array por conta da referncia.
    */
    private boolean isValido(int[][] jogo, int indice, int[] numeroDeSolucoes) {
        if (indice > 80){ // Se existir uma solução 
            return ++numeroDeSolucoes[0] == 1; // aumenta e retorna se for igual a 1
        }
        //não encontrado continua.
        //Calcula valor de x e y
        int x = indice % 9;
        int y = indice / 9;

        if (jogo[y][x] == 0) { //verificador de campp;
            List<Integer> numeros = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++){
                numeros.add(i); // preenche a lista com valores de 1 a 9
            }
            while (numeros.size() > 0) { //enquanto a lista possuir valores executa
                int numero = getProximoNumero(jogo, x, y, numeros); // pega proximo numero
                if (numero == -1){ //se valor igual a -1 (Evitar NullPointException que significa que tentou acessar uma posição inesistente)
                    break;
                }
                jogo[y][x] = numero; // não entrando no if a cima, então posição na matriz recebe valor  

                if (!isValido(jogo, indice + 1, numeroDeSolucoes)) { //recursividade para proximo indice, encontrar solução
                    jogo[y][x] = 0;
                    return false; //Mais de 1 solução encontrada 
                }
                jogo[y][x] = 0; // Restaura valor da posição da matriz para 0;
            }
        } else if (!isValido(jogo, indice + 1, numeroDeSolucoes)){ //Se não cama metodo recursivamente, se verdadeiro continua
            return false;
        }
        return true;
    }
    
    /*
    * Função copiar
    * Copia o jogo para não alterar a solução 
    */
    private int[][] copiar(int[][] jogo) {
        int[][] copia = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                copia[y][x] = jogo[y][x];
        }
        return copia;
    }
    
    
}
