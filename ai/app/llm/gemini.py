from google import genai

client = genai.Client(api_key="")

def generate(system_prompt: str, user_input: str):
    prompt = f"""
{system_prompt}

[입력]
{user_input}
"""

    response = client.models.generate_content(
        model="gemini-2.5-flash",
        contents=prompt
    )

    return response.text
