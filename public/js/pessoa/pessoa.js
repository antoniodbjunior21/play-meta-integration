var Pessoa = function () {
    const URL_SALVAR_PESSOA = '/pessoa/salvar'
    const URL_BUSCAR_PESSOAS = '/pessoas/filtrar'
    var $dataTable;

    var init = function (){
        iniciarExercicio1();
        iniciarTabela();
        iniciarFormPesquisa();
    }

    var iniciarFormPesquisa = function (){
        var $searchForm = $('#formPesquisa');

        $searchForm.submit(function (e) {
            e.preventDefault();
            $dataTable.ajax.reload(null, false);
        });

        $searchForm.on('click', '#buttonPesquisar', function (e) {
            $searchForm.submit();
        });

        $searchForm.on('click', '#buttonLimpar', function (e) {
            e.preventDefault();
            $searchForm.trigger("reset");
            $dataTable.ajax.reload(null, false);
        });
    }

    var iniciarTabela = function (){

       $dataTable = $('#dataTable').DataTable({
            responsive: true,
            dom: 't<"dt-panelfooter clearfix"<"row m-0"<"col-md-6 p-0" i><"col-md-6 p-0 dataTables_pager" p>>>',
            pageLength: 20,
            "processing": true,
            "serverSide": true,
            "autoWidth": false,
            "ajax": {
                "url": URL_BUSCAR_PESSOAS,
                "type": "POST",
                "data": function (d) {
                    var $searchForm = $('#formPesquisa');
                    var formData = $searchForm.serializeArray();

                    for (var i = 0; i < formData.length; i++) {
                        d[formData[i].name] = formData[i].value;
                    }

                    if (d.order !== undefined) {
                        for (var j = 0; j < d.order.length; j++) {
                            d["orderBy[" + j + "].column"] = d.order[j].column;
                            d["orderBy[" + j + "].dir"] = d.order[j].dir;
                        }
                    }

                    delete(d.columns);
                    delete(d.search);
                    delete(d.order);
                }
            },
            "columns": [
                {
                    data: 'nome'
                },
                {
                    data: 'ativo',
                    width: '75px'
                },
                {
                    data: 'id',
                    width: '80px'
                }
            ],
            "columnDefs":  [
                /*
                {
                    targets: 0,
                    render: function (data, type, full, meta) {
                        var letra = full.nome.substring(0, 1);
                        var linha1 = "#" + full.codigo + " - " + full.nome;
                        var linha2 = "";

                        if (full.tipo) {
                            linha2 = full.tipo === "FISICA" ? "Pessoa Física" : "Pessoa Jurídica";
                        } else {
                            linha2 = "Tipo não informado";
                        }

                        return GridRenderer.letterWithInfoRandomColor(letra, linha1, linha2);
                    }
                },
                {
                    targets: -2,
                    render: function(data, type, full, meta) {
                        return GridRenderer.statusBadgeBoolean(data, "Ativo", "Inativo");
                    }
                },
                {
                    targets: -1,
                    orderable: false,
                    render: function(data, type, full, meta) {
                        var acoesHtml = '';
                        acoesHtml += GridRenderer.editButton(data);
                        acoesHtml += GridRenderer.removeButton(data, "ml-2");
                        return acoesHtml;
                    }
                }*/
            ],
            "ordering": true,
            "sorting": []
        });

    }

    var iniciarExercicio1 = function (){
        $('#formExercicio1').validate({
            ignore: 'input[type=hidden]',
            rules:{
                nome: {
                    required: true
                }
            },
            messages: {
                nome: {
                    required: 'Informe o nome'
                }
            },
            errorClass: "invalid-feedback-error text-danger",
            submitHandler: function (form) {

                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR_PESSOA,
                    data: formData,
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    success: function () {
                        Swal.fire({
                            title: "Tudo certo :)",
                            text: "Pessoa salva com sucesso!",
                            icon: "success"
                        });
                    },
                    error: function (jqXHR, exception) {
                        Swal.fire({
                            title: 'Oops!',
                            text: 'Algo deu errado, tente novamente mais tarde',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        })
                    }
                });
            }
        });
    }
    return{
        init: function (){
            init();
        }
    }
}()