"""
Install the Google AI Python SDK

$ pip install google-generativeai
"""

import pathlib
import textwrap
import os

import google.generativeai as genai

from IPython.display import display
from IPython.display import Markdown


def to_markdown(text):
    text = text.replace("•", "  *")
    print(text)
    return Markdown(textwrap.indent(text, "> ", predicate=lambda _: True))

os.environ["GEMINI_API_KEY"] = 'AIzaSyDPwsWtk_tcdP6vNQZV0GH4NeaTUi7SsZY'
genai.configure(api_key=os.environ["GEMINI_API_KEY"])

# Create the model
generation_config = {
  "temperature": 1,
  "top_p": 0.95,
  "top_k": 64,
  "max_output_tokens": 8192,
  "response_mime_type": "text/plain",
}

model = genai.GenerativeModel(
  model_name="gemini-1.5-flash",
  generation_config=generation_config,
  # safety_settings = Adjust safety settings
  # See https://ai.google.dev/gemini-api/docs/safety-settings
  tools='code_execution',
)

# Model
model = genai.GenerativeModel("gemini-1.5-flash")
chat = model.start_chat(history=[])

# # response 
# print('--------------------------------response--------------------------------')

# response = chat.send_message(
#     "In one sentence, explain how a computer works to a women."
# )
# to_markdown(response.text)

# print('chat history', chat.history)

# keep sending message
while 1:
    print('--------------------------------keep sending message--------------------------------')
    x = input()
    response = chat.send_message(
        x, stream=True
    )

    for chunk in response:
        print(chunk.text)
        print("_" * 80) 
        
    # history
    print('--------------------------------history--------------------------------')
    for message in chat.history:
        display(to_markdown(f"**{message.role}**: {message.parts[0].text}"))