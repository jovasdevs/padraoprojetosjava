package br.com.jovasdevs.padraospring.service;

import br.com.jovasdevs.padraospring.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no dominio de cliente.
 * Com isso, se necessário, podemos ter multpiplas implementações dessa mesma interface
 */

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);

    void atualizar(Long id, Cliente cliente);
    void deletar(long id);
    void inserir(Cliente cliente);

}
