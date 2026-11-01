# ÉPREUVE TECHNIQUE DE TEST ADRIA PFE 2027
**PAR** : ELCADI HAMZA GLSID 3

**ÉPREUVE** : [PDF](/Test%20ADRIA%20ENSET%202027.pdf)

---
## Partie B — Conception et Architecture
### Diagramme de classes :
```mermaid

classDiagram
    class Account {
        +UUID id
        +String customerReference
        +AccountStatus status
        +String currency
        +double balance
    }

    class Transaction {
        +UUID id
        +UUID accountId
        +BigDecimal amount
        +String currency
        +String merchantCategory
        +String country
        +Instant createdAt
    }

    class AccountStatus {
        <<enumeration>>
        ACTIVE
        BLOCKED
    }

    Account "1" <-- "0..*" Transaction : accountId
    Account --> AccountStatus : status

```

### Schéma d’architecture :
```mermaid

flowchart TB
    FE["Angular Frontend"]
    GW["API Gateway"]
    DS["Discovery Service (Eureka)"]
    CS["Config Service"]
    AS["Account Service"]
    TS["Transaction Service"]

    FE --> GW

    GW <--> DS

    AS <--> DS
    TS <--> DS

    CS --> GW
    CS --> AS
    CS --> TS

    TS -->|"OpenFeign"| AS

```

## Partie C — Développer les micro-services fonctionnels en les exposant via des connecteurs REST, GraphQL :
### Account-Service :
![Account service getAll](/screenShots/1.png) \
![Account service getById](/screenShots/2.png) \
![Account service Swager](/screenShots/3.png) \
![Account service graphQL getAll](/screenShots/4.png) \
![Account service graphQL save](/screenShots/5.png) 

### Transaction-Service et communication via OpenFeign :
![Transaction service getAll](/screenShots/6.png) \
![Transaction service graphQL getAll](/screenShots/7.png) \
![Transaction service graphQL getById](/screenShots/8.png) \
![Transaction service graphQL save](/screenShots/9.png) \
![Transaction service swagger](/screenShots/10.png) 

### Une solution de tolérance aux pannes basée sur Resilience4J :
![Transaction service while the account service is shutdown](/screenShots/10_5.png) 

## Partie D — Développer les micro-services techniques :
### Spring Cloud Gateway et Eureka Discovery service :
![accounts from gateway-service](/screenShots/11.png) \
![transactions from gateway-service](/screenShots/12.png) 

### Config- service avec un un paramètre bank.transaction.max-amount=1000

![config repo](/screenShots/13.png) \

GITHUB REPO : https://github.com/Elcadi-Hamza/elcadi-hamza-test-adria-pfe-27-config

