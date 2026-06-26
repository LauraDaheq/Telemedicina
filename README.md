# Telemedicina — Symptom Checker (Android, Java)

Aplicación Android básica (esqueleto) para evaluación de síntomas relacionados con COVID-19 y contacto con la EPS correspondiente. Esta versión es un prototipo educativo: NO sustituye el diagnóstico médico.

Características:
- Cuestionario de síntomas (fiebre, tos, dificultad respiratoria, pérdida de olfato/gusto, dolor de garganta, fatiga).
- Evaluador de riesgo (bajo/medio/alto) según puntuación simple.
- Mapeo EPS → contacto (teléfono y email) desde assets/eps.json.
- Opciones para marcar llamada o enviar correo al contacto de la EPS vía intents.

Cómo usar
1. Abrir el proyecto en Android Studio.
2. Rellenar `app/src/main/assets/eps.json` con los contactos reales de las EPS.
3. Ejecutar la app en un dispositivo físico o emulador.

Aviso legal
Este proyecto es educativo y no ofrece diagnóstico. Siempre seguir las indicaciones de las autoridades sanitarias y profesionales de la salud.
