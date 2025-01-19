# ServerMoveMessage
## Overview / 概要
This BungeeCord plugin provides custom join, switch, and leave messages for players on your Minecraft server network. Messages are fully configurable, can include server-specific names, and support color codes. Permissions can also control who sees specific messages.

このBungeeCordプラグインは、ネットワーク入出時のメッセージを自由に設定する機能を提供します。メッセージはカラーコードに対応し、サーバ名を表示できます。また、権限を利用して、メッセージの表示対象を制御できます。

## Features / 機能
- Custom messages for network join, server switch, and leave events.
  - Includes placeholders for player names, server names, and more.
- Supports color codes using `&` (e.g., `&a` for green).
- Configurable message visibility based on permissions (`servermovemessage.admin`).
- Server name customization via `config.yml`.
- Default `config.yml` is automatically generated.

- ネットワーク入出時の自由メッセージ設定
  - プレイヤー名やサーバー名などのプレースホルダー対応
- `&` を使用したカラーコードをサポート
- 権限 (`servermovemessage.admin`) に基づくメッセージ表示制御
- `config.yml` でサーバ名を自由設定
- デフォルトの `config.yml` を自動生成

## Installation / インストール
1. Download the plugin jar file and place it in your BungeeCord server's `plugins` folder.
2. Start the server to generate the default `config.yml`.
3. Edit `config.yml` as needed to customize messages and server names.
4. Restart the server to apply changes.

1. プラグインのjarファイルをダウンロードし、BungeeCordサーバーの `plugins` フォルダーに搭載
2. サーバーを起動し、デフォルトの `config.yml` を生成
3. `config.yml` を編集し、メッセージやサーバ名をカスタマイズ
4. サーバーを再起動し、変更を適用

## Permissions / 権限
- `servermovemessage.admin`
  - Allows viewing network join and leave messages.
  - ネットワーク入出時のメッセージの表示を許可

## Configuration Example / 設定例
```yaml
# Message settings and toggles
# Placeholders:
# {player} - Player name
# {server} - Server name
# {fromServer} - Previous server name

messages:
  joinNetwork: "&a{player} has joined the network."
  switchServer: "&b{player} moved to {server}."
  joinServer: "&e{player} joined from {fromServer}."
  leaveNetwork: "&c{player} has left the network."
  firstJoin: "&a{player} has joined the game."

enabled:
  joinNetwork: true
  switchServer: true
  joinServer: true
  leaveNetwork: true

servers:
  hub: "HUB Server"
  server1: "Main Server"
  server2: "Backup Server"
```

```yaml
# メッセージ設定と切替
# プレースホルダー:
# {player} - プレイヤー名
# {server} - サーバー名
# {fromServer} - 前サーバー名

messages:
  joinNetwork: "&a{player}がネットワークから接続しました。"
  switchServer: "&b{player}が{server}に移動しました。"
  joinServer: "&e{player}が{fromServer}から入室しました。"
  leaveNetwork: "&c{player}がネットワークから退出しました。"
  firstJoin: "&a{player}がゲームに参加しました。"

enabled:
  joinNetwork: true
  switchServer: true
  joinServer: true
  leaveNetwork: true

servers:
  hub: "HUB高速"
  server1: "メインサーバー"
  server2: "予備サーバー"
```

