## Runtracker
간단한 달리기 추적기 만들어보자 

### Tech Stack

- Server
    - 실시간으로, 매우 짧은 간격으로 좌표값을 수신받고, 클라이언트에 정보를 뿌려야 한다.
    - 각 요청을 처리하는데 필요한 연산의 부담은 크지 않다. 
    - 이런 목적에는 Reactive Stack이 좋아보인다. 
        - Spring Webflux + Kotlin
        - Spring Data Reactive MongoDB
- Web FE
    - React(JS)
- Mobile
    - React Native(Expo.io Managed WorkFlow)
        - 추후 watchOS 등 연동할 필요가 있으면, `Bare Workflow`로 전환
