Como rodar testes automatizados (projeto simples sem build tool)

Este projeto não usa Maven/Gradle por padrão e não possui JUnit configurado. Para manter mudanças mínimas, foram adicionados testes automatizados simples via uma classe Java executável (TestRunner) que imprime [TEST_PASS] ou [TEST_FAIL] no console.

Como executar:
1) Compile todo o projeto (ex.: via IDE ou `javac`), garantindo que o classpath inclua a pasta src.
2) Execute a classe `br.com.ada.ecommerce.tests.TestRunner`.
3) Verifique a saída do console. Todos os testes devem exibir [TEST_PASS].

O que é coberto:
- Fluxo do pedido: finalizar -> pagar -> entregar (FINALIZADO).
- Validações do Pedido: não permite cliente nulo, quantidade inválida.
- Estoque: decremento ao adicionar ao carrinho e devolução no ajuste/remoção.

Observação: Este é um test harness simples, não substitui um framework de testes completo.