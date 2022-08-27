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
        alert("Produto cadastrado com sucesso")
        limparFormulario()
      }
    })
  }
  const limparFormulario = () => {
    setObjProdutos(produto)
  }
  return (
    <div>
      <Formulario botao = {btnCadastrar} eventoTeclado = {aoDigitar} cadastrar = {cadastrar} obj = {objProduto}/>
      <Tabela vetor = {produtos}/>
    </div>
  );
}

export default App;
