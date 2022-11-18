import { useEffect, useState } from "react";
import "./App.css"
import Formulario from "./Formulario";
import Tabela from "./Tabela/indes";


function App() {

  const produto = {
    codigo: 0,
    nome: '',
    marca: ''
  }
  const [objProduto, setObjProdutos] = useState(produto)
  const [btnCadastrar, setBtnCadastrar] = useState(true)
  const [produtos, setProdutos] = useState([])

  useEffect(() => {
    fetch("http://localhost:8080/listar")
    .then(retorno => retorno.json())
    .then(retornoConvertido => setProdutos(retornoConvertido))
  }, [])
  //otendo os dados do 
  const aoDigitar = (e) =>{
    setObjProdutos({...objProduto, [e.target.name]:e.target.value})
  }
  //cadastrar
  const cadastrar = () => {
    fetch("http://localhost:8080/cadastrar", {
      method: 'post',
      body: JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept': 'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        setProdutos([...produtos, retorno_convertido])
        limparFormulario()
      }
    })
  }
  const limparFormulario = () => {
    setObjProdutos(produto)
    setBtnCadastrar(true)

  }
  //selecionar produto
  const selecionar_Produtos = (indice) => {
    setObjProdutos(produtos[indice])
    setBtnCadastrar(false)
  }
  //remover
  const remover = () => {
    fetch("http://localhost:8080/remover/"+objProduto.codigo, {
      method: 'delete',
      body: JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept': 'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      //mensagem
      alert(retorno_convertido.mensagem)

      // copia do vetor de produtor
      let vetorTemp = [...produtos];

      //indice
      let indice = vetorTemp.findIndex((p) => {
        return p.codigo === objProduto.codigo
      })
      //remover prduto do vetor temp
      vetorTemp.splice(indice, 1)

      //atualizar  vetor de produtos
      setProdutos(vetorTemp)

      //limpar formulario 
      limparFormulario()
    })
  }
  //alterar
  const alterar = () => {
    fetch("http://localhost:8080/alterar", {
      method: 'put',
      body: JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept': 'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        
      // copia do vetor de produtor
      let vetorTemp = [...produtos];

      //indice
      let indice = vetorTemp.findIndex((p) => {
        return p.codigo === objProduto.codigo
      })
      //alterar produto do vetor temp
      vetorTemp[indice] = objProduto

      //atualizar  vetor de produtos
      setProdutos(vetorTemp)

      limparFormulario()
      }
    })
  }
  return (
    <div>
      <Formulario 
        botao = {btnCadastrar} 
        eventoTeclado = {aoDigitar} 
        cadastrar = {cadastrar} 
        obj = {objProduto}
        cancelar = {limparFormulario}
        remover = {remover}
        alterar = {alterar}/>
      <Tabela vetor = {produtos} selecionar = {selecionar_Produtos}/>
    </div>
  );
}

export default App
