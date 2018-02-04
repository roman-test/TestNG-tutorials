# TestNG-tutorials
Примеры создания тестов на Java + maven + IDEA + Selenium 

Анализ всей этой хрени:
 - Selenide пришлось вообще выкинуть т.к. в нём невозможно юзать более одного экземпляра вебдрайвера (параллельный запуск селениум тестов в нескольких окошках браузера невозможен), что очень плохо
 - По умолчанию TestNG запускает классы параллельно 
 - По умолчанию создается конфиг в котором один Suite и один Test внутри которого классы - вот все классы выполняются внутри одного теста параллельно
 Не работают настройки параллельности parallel и т.п. - запретить параллельное выполнение класов внутри теста невозможно
 
 Чтобы выполнять тесты из нескольких классов без параллельности, нужно разнести их по отдельным тестам, для этого необходимо создать свой testng.xml конфиг:

     <?xml version="1.0" encoding="UTF-8"?>
     <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
     
     <suite name="Интерграционные тесты" parallel="tests">

     <test name="Первый тест">
        <classes>
          <class name="IntergationTests.TestClass1"/>
       </classes>
     </test>

    <test name="Второй тест">
       <classes>
         <class name="IntergationTests.TestClass2"/>
      </classes>
    </test>

    <test name="Третий тест">
      <classes>
        <class name="IntergationTests.TestClass3"/>
     </classes>
     </test>

    </suite>
    
  IntergationTests - это просто имя пакета, его нужно в первой строке всех файлов классов вставить, 
  т.е. файлы классов наших тестов будут начинаться ка-то так:
    
    package IntergationTests;

    import org.testng.Assert;
    import org.testng.Reporter;
    import org.openqa.selenium.remote.RemoteWebDriver;
    ...
    public class TestClass1 {
  
 
  в конфиге testng.xml можно уже включать ( с помощью parallel="tests") или отключать параллельное выполнение тестов (внутри теста по одному классу) - это по крайней мере работает, но только в версии TestNG до 6.11 включительно - далее с 6.13 вообще сломано управление параллельностью и никакие настройки не действуют
 
 - Чтобы подключить кофиг testng.xml к проекту , на него нужно сослаться в pom.xml файле (выполняемого maven-ом),
 для этого добавляем в pom.xml такую хрень:
 
 после блока "dependencies"

    <build>
        <plugins>
             <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.20</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
жесть .. столько букв  - просто сказали что нужно юзать наш testng.xml =)

- тесты запускать можно и в IDEA (правой кнопкой на testng.xml -> run) 
  и с консоли: mvn test
  
  

