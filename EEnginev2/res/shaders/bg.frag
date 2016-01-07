#version 330 core

layout (location = 0) out vec4 color;


in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec4 inColor;

void main()
{
	color = texture(tex, fs_in.tc)*inColor;
	if (color.w < 1.0)
		discard;
}