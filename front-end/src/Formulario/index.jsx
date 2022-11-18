const Formulario = ({botao, eventoTeclado, cadastrar, obj, cancelar, remover, alterar}) => {

    return(
        <form>
            <input type="text" name = "nome" value = {obj.nome} onChange={eventoTeclado} placeholder="Nome" className="form-control"/>
            <input type="text" name = "marca" value = {obj.marca} onChange={eventoTeclado} placeholder="Marca" className= "form-control"/>
            {
                botao
                ?
                <input type="button" onClick = {() => cadastrar()} value = "Cadastrar"  className="btn btn-primary" />
                :
                <div>
                    <input type="button" value = "Alterar"  onClick = {alterar} className="btn btn-warning"/>
                    <input type="button" value = "Remover" onClick = {remover} className="btn btn-danger"/>
                    <input type="button" value = "Cancelar" onClick={cancelar} className="btn btn-secondary"/>
                </div>
            }
            
        </form>
    )
}
export default Formulario