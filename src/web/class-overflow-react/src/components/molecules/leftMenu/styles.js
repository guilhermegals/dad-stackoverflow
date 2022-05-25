import styled from 'styled-components'

export const TagContent = styled.div`
  justify-content: space-around;
  height: 100vh;
  font-size: 16px;
  color: black;
  top:50px;
  --rti-bg: #fff;
    --rti-border: #ccc;
    --rti-main: #3182ce;
    --rti-radius: 0.375rem;
    --rti-s: 0.5rem;
    --rti-tag: #edf2f7;
    --rti-tag-remove: #e53e3e;
    align-items: center;
    bg: var(--rti-bg);
    border: 1px solid var(--rti-border);
    border-radius: var(--rti-radius);
    display: flex;
    flex-wrap: wrap;
    gap: var(--rti-s);
    line-height: 1.4;
    padding: var(--rti-s);
`
