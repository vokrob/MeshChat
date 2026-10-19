# MeshChat

Учебный mesh-мессенджер для Android: устройства обмениваются текстовыми сообщениями без интернета и серверов, пересылая пакеты через промежуточные узлы (multi-hop). Содержимое сообщений сквозно шифруется.

## Цели

- Практика современного Android-стека: Kotlin, Jetpack Compose (Material 3), MVVM, Hilt, Room, Coroutines/Flow.
- Реализация mesh-маршрутизации: controlled flooding, дедупликация, TTL, store-and-forward, ACK.
- Практика прикладной криптографии на Google Tink: Ed25519, X25519, AEAD.
- Целевой демо-сценарий: три устройства A — B — C, где A и C не видят друг друга напрямую; сообщение A → C доходит через B, а при отсутствии B ждёт в очереди.

Проект выполняется в рамках курса «Разработка мобильных приложений».

## Архитектура

<!-- Схема архитектуры будет добавлена позже (см. TASKS.md, задача 125). -->

Направление зависимостей: `ui → domain → data`. Обратных зависимостей быть не должно.

Модули проекта:

```
app/                    # Application, MainActivity, навигация, Hilt-граф
core/
  common/               # qualifier'ы диспетчеров, общий DI
  model/                # общие модели (PeerId, MessageId, ...)
  mesh/                 # MeshTransport, MeshRouter, формат пакета, дедупликация
  crypto/               # ключи, подписи, шифрование (обёртка над Tink)
  database/             # Room: сущности, DAO, миграции
  ui/                   # тема Material 3, общие Compose-компоненты
feature/                # появится на задаче 12: chat, peers, onboarding
```

## Технологии

- Kotlin, Coroutines + Flow
- Jetpack Compose + Material 3, Navigation Compose
- Hilt (DI), Room (локальная история), DataStore (профиль)
- kotlinx.serialization (Protobuf) — формат пакета
- Google Tink — подписи и шифрование
- Nearby Connections (Play Services) — основной транспорт
- ktlint + detekt, JUnit

## Сборка

```bash
./gradlew assembleDebug
```

Проверки перед коммитом:

```bash
./gradlew ktlintCheck detekt testDebugUnitTest assembleDebug
```

Требования: JDK 17+, Android SDK (`minSdk` 28).

## Статус

Проект в начале разработки, текущий этап — 1 (каркас приложения). План задач: [TASKS.md](TASKS.md).
