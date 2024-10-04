"""
Install the Google AI Python SDK

$ pip install google-generativeai
"""

import os
import google.generativeai as genai

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

chat_session = model.start_chat(
  history=[
    {
      "role": "user",
      "parts": [
        "hello\n",
      ],
    },
    {
      "role": "model",
      "parts": [
        "Hello! 👋  How can I help you today? 😊 \n",
      ],
    },
  ]
)

response = chat_session.send_message("INSERT_INPUT_HERE")

print(response.text)
