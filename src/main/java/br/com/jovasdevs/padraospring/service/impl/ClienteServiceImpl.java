package br.com.jovasdevs.padraospring.service.impl;

import br.com.jovasdevs.padraospring.model.Cliente;
import br.com.jovasdevs.padraospring.model.ClienteRepository;
import br.com.jovasdevs.padraospring.model.Endereco;
import br.com.jovasdevs.padraospring.model.EnderecoRepository;
import br.com.jovasdevs.padraospring.service.ClienteService;
import br.com.jovasdevs.padraospring.service.ViaCepService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    //TODO Singleton: Injetar os componentes do Spring com @Autowired

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;
    //TODO Strategy: Implementar os métodos definidos na interface.
    //TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        //FIXME Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        //verificar se o endederço do cliente ja existe (pelo cep)
        salvarClienteComCep(cliente);

    }



    @Override
    public void atualizar(Long id, Cliente cliente) {
        //Buscar Cliente po Id, caso exista
        Optional<Cliente> clientebd = clienteRepository.findById(id);
        if(clientebd.isPresent()){
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(long id) {
    //Deletar Cliente por Id

        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //Caso não exista, integrar com ViaCep e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return  novoEndereco;
        });
        cliente.setEndereco(endereco);
        //Inserrir Cliente, vinculando o Endereço (novo ou existente)
        clienteRepository.save(cliente);
    }

}
