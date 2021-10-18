import requests
import re
from telethon import TelegramClient, events, sync, utils

api_id = '8139422'
api_hash = '13177338dd1dc27e41ddc35b5ae6bbcb'
client = TelegramClient('anon', api_id, api_hash)
baseAddress = "localhost:8085"

url = f"http://{baseAddress}/api/v1/tel/message"

receiverList = {
    'MO - TGS Filtered Trading Ideas': True,
    'Share Market Trading Equity': True,
    'Intraday Trading Equity Stock': True
}


def getHeader():
    return {
        'Content-type': 'application/x-www-form-urlencoded'
    }


def sendMessage(sender, message):
    payload = {
        'sender': sender,
        'message': message
    }
    return requests.post(url, data=payload, headers=getHeader())


def clean(message):
    msg = re.sub(r'\n+', r'\n', message)
    msg = re.sub(r'\n', r'$', msg)
    msg = re.sub(' +', ' ', msg)
    msg = re.sub(r'[^\x00-\x7f]', r'', msg)

    return msg


@client.on(events.NewMessage)
async def my_event_handler(event):
    print("------------------------------------------")
    sender = await event.get_sender()
    sender_name = utils.get_display_name(sender)

    if receiverList.get(sender_name) is not None and receiverList.get(sender_name):
        res = sendMessage(sender_name, clean(event.raw_text))
        if res.status_code == 200:
            print(f"Sent Successfully, Sender: {sender_name}, Message: {clean(event.raw_text)}")

        else:
            print(
                f"Error in Sending, StatusCode: {res.status_code}, Sender: {sender_name}, Message: {clean(event.raw_text)}")

    else:
        print("Ignoring Message From Sender: " + sender_name)

    print("------------------------------------------\n")


print("Launched")

with client:
    client.run_until_disconnected()

# client.start()
# client.run_until_disconnected()
#   with client:
#       client.run_until_disconnected()

# python3 -u readAll.py > log &
# nohup python3 -u readAll.py &
