# Spring Boot 学习笔记
### 事件监听
* 构建Application
```text
SpringApplication app = new SpringApplication(Application.class);
```
* 添加监听事件
```text
app.addListeners(
    (applicationEvent) -> {
        if (applicationEvent instanceof ApplicationReadyEvent) {
            log.info("Application had already run!!!");
        }
    }
);
```
* 运行
```text
app.run(args);
```
### 数据库配置
* MySQL
    * 依赖
    ```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
    * 配置
    ```yaml
    spring:
      datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/test
        username: root
        password: 123456
    ```
### 使用MyBatis Plus
* 依赖
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.2.0</version>
</dependency>
```
* 配置
```yaml
mybatis-plus:
  mapper-locations: mapper/**/*.xml
  configuration:
    map-underscore-to-camel-case: false
  check-config-location: false
```
* 用法
    * 扫描包，添加分页插件
    ```java
    @Configuration
    @MapperScan("com.bzdnet.learn.springboot.dao")
    public class MyBatisPlusConfiguration {
    
       @Bean
       public PaginationInterceptor paginationInterceptor() {
           PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
           return paginationInterceptor;
       }
    
    }
    ```
    * 添加领域对象
    ```java
    @Data
    @ToString
    @TableName("t_user")
    public class UserEntity {
    
        @TableId(value = "id_", type = IdType.AUTO)
        private Long id;
    
        @TableField("account_")
        private String account;
    
        @TableField("age_")
        private int age;
    
        @TableField("disabled_")
        private int disabled;
    
    }
    ```
    * 添加DAO层接口
    ```java
    public interface UserMapper extends BaseMapper<UserEntity> {
        List<UserEntity> selectListBetweenAge(@Param("min") int min, @Param("max") int max);
    }
    ```
    * 添加mapper文件 (自定义sql时使用)
    ```xml
    <mapper namespace="com.bzdnet.learn.springboot.dao.UserMapper">
    
        <resultMap id="baseRM" type="com.bzdnet.learn.springboot.entity.UserEntity">
            <id column="id_" property="id" />
            <result column="account_" property="account"/>
            <result column="age_" property="age"/>
            <result column="disabled_" property="disabled"/>
        </resultMap>
    
        <select id="selectListBetweenAge" resultMap="baseRM">
            select id_,account_,age_,disabled_
            where age_ between #{min} and #{max}
        </select>
    
    </mapper>
    ```
    * 添加Service层
    ```java
    @Service
    public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    
        public List<UserEntity> selectListBetweenAge(int min, int max) {
            return baseMapper.selectListBetweenAge(min, max);
        }
    
    }
    ```
    * Controller层调用
        * 一般查询
        ```text
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq("disabled_", form.getDisabled());
        return userService.list(queryWrapper);
        ```
        * 分页查询
        ```text
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq("disabled_", form.getDisabled());
        Page<UserEntity> page = new Page<>(form.getCurrent(), form.getSize());
        IPage<UserEntity> pageList = userService.page(page, queryWrapper);
        return pageList;
        ```
        * 插入
        ```text
        userService.save(entity);
        ```
        * 更新
        ```text
        userService.updateById(entity);
        ```
        * 删除
        ```text
        userService.removeById(id);
        ```
### 使用Jpa
* 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
* 配置
```yaml
spring: 
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
```
* 用法
    * 启用Jpa
    ```java
    @Configuration
    @EnableJpaRepositories(basePackages = {"com.bzdnet.learn.springboot.dao"})
    @EntityScan(basePackages = {"com.bzdnet.learn.springboot.entity"})
    public class JpaConfiguration {}
    ```
    * 添加领域对象
    ```java
    @Data
    @ToString
    @Entity
    @Table(name = "t_user")
    public class UserEntity {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_")
        private Long id;
    
        @NotEmpty
        @Column(name = "account_")
        private String account;
    
        @Column(name = "age_")
        @Max(120)
        @Min(0)
        private int age;
    
        @Column(name = "disabled_")
        private int disabled;
    
    }
    ```
    * 添加DAO层接口
    ```java
    public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {}
    ```
    * Service层调用
    ```java
    @Service
    public class UserService {
        
        @Autowired
        private UserRepository userRepository;
      
        public void jpaTest(){
            Iterator<UserEntity> all = userRepository.findAll().iterator();
            Page<UserEntity> page = userRepository.findAll(PageRequest.of(1, 10));
            userRepository.save(new UserEntity());
            userRepository.saveAll(new ArrayList<>());
            userRepository.deleteById(1L);
        }
    
    }
    ```
### 统一异常处理
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
    * 自定义异常
    * @param ce
    * @return 
    */
    @ExceptionHandler(CustomException.class)
    public Result handleCustomException(CustomException ce) {
        return new Result(EnumResponseCode.CUSTOM_EXCEPTION.getCode(), ce.getMessage());
    }

    /**
    * 校验异常
    * @param ex
    * @return 
    */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public Result handleValidException(Exception ex) {
        return new Result(EnumResponseCode.VALID_EXCEPTION.getCode(), ex.getMessage());
    }

    /**
    * 通用异常
    * @param ex
    * @return 
    */
    @ExceptionHandler(Exception.class)
    public Result handleGlobalError(Exception ex) {
        ex.printStackTrace();
        return new Result(EnumResponseCode.ERROR.getCode(), ex.getMessage());
    }

}
```
### 数据校验
spring context包含validation依赖，所以无需额外导入  
`配合统一异常处理返回异常响应`
* 一般参数校验
    * Controller类上加@Validated注解
    * 方法参数上加需要的校验注解，如@NotNull,@Min,@Max
* 实体类参数校验
    * 实体类属性上加需要的校验注解，如@NotNull,@Min,@Max
    * 方法实体类参数上加@Validated注解 

`快速校验：遇到一个失败即返回`
```text
@Bean
public Validator validator() {
    return Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory().getValidator();
}
```
    
    

### SpringFox通用API文档
* 依赖
```xml
<dependencys>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.9.2</version>
    </dependency>
</dependencys>
```
* 配置
```java
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket swagger2DocketBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("Spring fox Swagger2").description("学习Springboot的过程笔记").contact(new Contact("Yushigui", "https://github.com/ysglyl", "yushigui1990@foxmail.com")).build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bzdnet.learn.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
```
* 用法   

注解|说明
---|---
@Api|申明一个Controller
@ApiOperation|申明一个请求
@ApiImplicitParams|包装多个参数
@ApiImplicitParam|申明请求参数
@ApiModel|申明数据实体，配合@RequestBody注解说明实体参数
@ApiModelProperty|申明实体属性
* 举例
```java
@ApiModel
public class UserEntity {

    @ApiModelProperty(value = "自增长的ID主键")
    private Long id;

}

@RestController
@Api(tags = {"用户相关的Rest Api接口"})
public class UserController {
    
    @ApiOperation(value = "插入用户信息")
    @PostMapping("/add")
    public Result insert(@RequestBody UserEntity entity) {
        return null;
    }
        
    @ApiOperation(value = "查询年龄范围内的用户列表", notes = "包含最大年龄和最小年龄")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min", value = "最小年龄（0-120）", dataType = "int"),
            @ApiImplicitParam(name = "max", value = "最大年龄（0-120）", dataType = "int")
    })
    @PostMapping("/selectListBetweenAge")
    public Result selectListBetweenAge(@RequestParam("min") int min, @RequestParam("max") int max) {
        return null;
    }
}
```

